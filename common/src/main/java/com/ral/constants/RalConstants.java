package com.ral.constants;

import java.nio.charset.Charset;

/**
 * 
 * @author victor
 * @desc 全局常量
 */
public final class RalConstants {
	
	private RalConstants(){}
	
	/**
	 * 默认字符集编码
	 */
	public static final String DEFAULT_CHARSET_NAME = "UTF-8";

	/**
	 * 默认字符集编码
	 */
	public static final Charset DEFAULT_CHARSET = Charset.forName(DEFAULT_CHARSET_NAME);
	
	/**
	 * session默认有效秒数 = 30分钟
	 */
	public final static int DEFAULT_SESSION_EXPIRE = 30 * 60;
	

	/**
	 * Token默认有效秒数 = 30分钟
	 */
	public final static int DEFAULT_TOKEN_EXPIRE = 30 * 60;
	
	
	/**
	 * Excel 默认导出记录数量
	 */
	public static final int DEFAULT_EXCEL_SIZE = 200000;
	
	

	/**
	 * HTTP请求默认超时时间
	 */
	public static final int DEFAULT_HTTP_TIMEOUT = 90000;
	
	
	
}
