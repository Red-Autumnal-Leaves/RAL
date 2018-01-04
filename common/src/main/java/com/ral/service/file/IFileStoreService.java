package com.ral.service.file;

import java.io.File;
import java.io.InputStream;

import com.aliyun.oss.model.ObjectMetadata;
import com.ral.model.domain.OssUploadResponse;

/**
 * 文件存储服务接口
 * @author victor
 *
 */
public interface IFileStoreService {
	
	String getBucketName();
	
	OssUploadResponse upload(File file, String fileId);
	
	OssUploadResponse upload(String url, String fileId);
	
	OssUploadResponse upload(byte[] bytes, String fileId);
	
	File download(File file, String fileId);
	
	InputStream download(String fileId);
	
	void delete(String fileId);
	
	ObjectMetadata getFileInfo(String fileId);
	
	String getUrl(String fileId, Long expires);
	
}
