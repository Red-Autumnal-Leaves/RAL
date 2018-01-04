package com.ral.util.codec;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.ral.constants.RalConstants;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;


/**
 * 
 * @author victor
 * @desc base64工具类
 */
public class Base64Utils {

	private static Logger logger = Logger.getLogger(Base64Utils.class);

	/**
	 * 编码 Base64
	 *
	 * @param str
	 *            普通字符串
	 * @return
	 */
	public static String encode(String str) {
		if (!StringUtils.isNullOrEmpty(str)) {
			try {
				return Base64.encodeBase64String(str.getBytes(RalConstants.DEFAULT_CHARSET_NAME));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				logger.error("encode base64 error {}", e);
			}
		}
		return StringUtils.EMPTY;
	}

	/**
	 * 对文件进行编码 Base64
	 *
	 * @param file
	 *            文件
	 * @return
	 */
	public static String encode(File file) {
		InputStream in = null;
		byte[] data = null;
		try {
			in = new FileInputStream(file);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("encode file error {}", e);
			return null;
		}
		return new String(Base64.encodeBase64(data));
	}

	/**
	 * 将网络文件转换成Base64编码
	 *
	 * @param uri 文件uri
	 * @return
	 */
	public static String encodeFromUrl(String uri) {
		byte[] data = null;
		try {
			URL url = new URL(uri);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			InputStream inStream = conn.getInputStream();
			data = readInputStream(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (data == null) {
			return null;
		}
		return new String(Base64.encodeBase64(data));
	}
	
	/**
     * 将文件base64解码为文件
     * @param base64 base64编码
     * @param fileName 保存文件全路径
     * @return
     */
    public static void decode(String base64,String fileName) {
        try {
            byte[] b = Base64.decodeBase64(base64);
            for(int i=0; i<b.length; ++i) {
                if(b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(fileName);
            out.write(b);
            out.flush();
            out.close();
        }
        catch (Exception e) {
        	e.printStackTrace();
        	logger.error("decode to file error {}", e);
        }
    }

	/**
	 * 解码 Base64
	 *
	 * @param base64 Base64字符串
	 * @return
	 */
	public static String decode(String base64) {
		if (!StringUtils.isNullOrEmpty(base64)) {
			byte[] bytes = Base64.decodeBase64(base64);
			try {
				return new String(bytes, RalConstants.DEFAULT_CHARSET_NAME);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				logger.error("decode base64 error {}", e);
			}
		}
		return StringUtils.EMPTY;
	}

	public static byte[] readInputStream(InputStream inStream) {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		try {
			while ((len = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, len);
			}
			inStream.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return outStream.toByteArray();
	}
}
