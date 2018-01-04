package com.ral.util.codec;

import com.ral.constants.RalConstants;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 
 * @author victor
 * @desc 通用编码工具类
 */
public final class URLUtils {
	

	private URLUtils(){}
	
	 /**
     * 编码 URI
     *
     * @param url
     * @return
     */
    public static String encodeURI(String url) {
        if (!StringUtils.isNullOrEmpty(url)) {
            try {
                return URLEncoder.encode(url, RalConstants.DEFAULT_CHARSET_NAME);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        return StringUtils.EMPTY;
    }
    
    /**
     * 解码 URI
     *
     * @param url
     * @return
     */
    public static String decodeURI(String url) {
        if (!StringUtils.isNullOrEmpty(url)) {
            try {
                return URLDecoder.decode(url, RalConstants.DEFAULT_CHARSET_NAME);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        return StringUtils.EMPTY;
    }
}
