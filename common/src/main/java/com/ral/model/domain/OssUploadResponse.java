package com.ral.model.domain;

import java.io.Serializable;

/**
 * OSS存储上传返回信息封装
 * @author victor
 *
 */
public class OssUploadResponse implements Serializable{

	private static final long serialVersionUID = 12345423424454554L;

	private String fileId;
	
	private boolean success;
	
	private String url;
	
	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
