package com.ral.service.file.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.ral.constants.RalConstants;
import com.ral.util.codec.UUIDUtils;
import com.ral.exception.ServiceException;
import com.ral.model.domain.OssUploadResponse;
import com.ral.service.file.IFileStoreService;
import com.ral.util.codec.StringUtils;
import org.apache.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

/**
 * 阿里云OSS上传实现类
 */
public class OssFileStoreServiceImpl implements IFileStoreService {
	
	private static Logger logger = Logger.getLogger(OssFileStoreServiceImpl.class);

	public OssFileStoreServiceImpl(String endpoint,String key,String secret){
		this.endpoint = endpoint;
		this.key = key;
		this.secret = secret;
	}

	private String endpoint;

	private String key;

	private String secret;
	
	private static long expires = 1000 * 60 * 60 * 24 * 365 * 100;//100年

	@Override
	public OssUploadResponse upload(File file, String fileId) {
		fileId = StringUtils.isNullOrEmpty(fileId) ? UUIDUtils.uuid()  : fileId;
		OSSClient client = null;
		try{
			client = new OSSClient(endpoint, key, secret);
			client.putObject(getBucketName(),fileId,file);
			OssUploadResponse response = new OssUploadResponse();
			response.setSuccess(true);
			response.setFileId(fileId);
			response.setUrl(this.getUrl(getBucketName(), fileId, expires,client));
			return response;
		}catch(Exception ex){
			ex.printStackTrace();
			logger.error("OSS文件上传异常:",ex);
			throw new ServiceException("上传文件失败:" + ex.getMessage());
		}finally{
			client.shutdown();
		}
	}

	@Override
	public OssUploadResponse upload(String url, String fileId) {
		fileId = StringUtils.isNullOrEmpty(fileId) ? UUIDUtils.uuid()  : fileId;
		OSSClient client = null;
		try{
			client = new OSSClient(endpoint, key, secret);
			InputStream in = new URL(url).openStream();
			client.putObject(getBucketName(),fileId,in);
			OssUploadResponse response = new OssUploadResponse();
			response.setSuccess(true);
			response.setFileId(fileId);
			response.setUrl(this.getUrl(getBucketName(), fileId, expires,client));
			return response;
		}catch(Exception ex){
			ex.printStackTrace();
			logger.error("OSS文件上传异常:",ex);
			throw new ServiceException("上传文件失败:" + ex.getMessage());
		}finally{
			client.shutdown();
		}
	}

	@Override
	public OssUploadResponse upload(byte[] bytes, String fileId) {
		fileId = StringUtils.isNullOrEmpty(fileId) ? UUIDUtils.uuid()  : fileId;
		OSSClient client = null;
		try{
			client = new OSSClient(endpoint, key, secret);
			ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
			client.putObject(getBucketName(),fileId,bin);
			OssUploadResponse response = new OssUploadResponse();
			response.setSuccess(true);
			response.setFileId(fileId);
			response.setUrl(this.getUrl(getBucketName(), fileId, expires,client));
			return response;
		}catch(Exception ex){
			ex.printStackTrace();
			logger.error("OSS文件上传异常:",ex);
			throw new ServiceException("上传文件失败:" + ex.getMessage());
		}finally{
			client.shutdown();
		}
	}

	@Override
	public File download(File file, String fileId) {
		OSSClient client = null;
		try{
			client = new OSSClient(endpoint, key, secret);
			client.getObject(new GetObjectRequest(getBucketName(), fileId), file);
			return file;
		}catch(Exception ex){
			ex.printStackTrace();
			logger.error("OSS文件下载异常:",ex);
			throw new ServiceException("OSS文件下载失败:" + ex.getMessage());
		}finally{
			client.shutdown();
		}
	}

	@Override
	public InputStream download(String fileId) {
		OSSClient client = null;
		try{
			client = new OSSClient(endpoint, key, secret);
			OSSObject ossObject = client.getObject(getBucketName(), fileId);
			return ossObject.getObjectContent();
		}catch(Exception ex){
			ex.printStackTrace();
			logger.error("OSS文件下载异常:",ex);
			throw new ServiceException("下载文件失败:" + ex.getMessage());
		}finally{
			client.shutdown();
		}
	}
	

	@Override
	public void delete(String fileId) {
		OSSClient client = null;
		try{
			client = new OSSClient(endpoint, key, secret);
			client.deleteObject(getBucketName(), fileId);
		}catch(Exception ex){
			ex.printStackTrace();
			logger.error("OSS文件删除异常:",ex);
			throw new ServiceException("上传删除失败:" + ex.getMessage());
		}finally{
			client.shutdown();
		}
	}

	@Override
	public ObjectMetadata getFileInfo(String fileId) {
		OSSClient client = null;
		try{
			client = new OSSClient(endpoint, key, secret);
	        return this.getFileMeta(getBucketName(), fileId, client);
		}catch(Exception ex){
			ex.printStackTrace();
			logger.error("OSS获取文件信息异常:",ex);
			throw new ServiceException("OSS获取文件信息失败:" + ex.getMessage());
		}finally{
			client.shutdown();
		}
	}

	@Override
	public String getUrl(String fileId, Long expires) {
		OSSClient client = null;
		try{
			client = new OSSClient(endpoint, key, secret);
	        return this.getUrl(getBucketName(), fileId, expires,client);
		}catch(Exception ex){
			ex.printStackTrace();
			logger.error("OSS文件获取URL异常:",ex);
			throw new ServiceException("OSS获取文件URL失败:" + ex.getMessage());
		}finally{
			client.shutdown();
		}
	}
	
	private String getUrl(String bucketName, String fileId, Long expires,OSSClient client){
		client = new OSSClient(endpoint, key, secret);
		long times = expires == null ? OssFileStoreServiceImpl.expires : expires;
		Date expiration = new Date (new Date().getTime() + times);
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, fileId);
        request.setExpiration(expiration);
        URL url = client.generatePresignedUrl(request);
        return url.toString();
	}
	
	private ObjectMetadata getFileMeta(String bucketName, String fileId,OSSClient client){
		ObjectMetadata metadata = client.getObjectMetadata(bucketName, fileId);
		return metadata;
	}

	@Override
	public String getBucketName() {
		return RalConstants.OSS_DEFAULT_BUCKET_NAME;
	}

	@Override
	public void testConfig() {
		System.out.println(this.endpoint);
	}
}
