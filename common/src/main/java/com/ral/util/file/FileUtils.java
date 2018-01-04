package com.ral.util.file;

import com.ral.util.codec.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * 
 * @author victor
 * @dec 文件工具类
 */
public final class FileUtils {

	private FileUtils() {
	}

	/**
	 * 截取文件名称，获取文件后缀名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String prefix(String fileName) {
		if (fileName.lastIndexOf(".") > -1) {
			String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
			return prefix.toUpperCase();
		}
		return null;
	}

	/**
	 * 截取文件名称，获取文件后缀名
	 * 
	 * @param file
	 * @return
	 */
	public static String prefix(File file) {
		String fileName = file.getName();
		return prefix(fileName);
	}

	/**
	 * 校验是否为伪装文件，后缀名和实际内容类型是否一致
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean checkFileName(String fileName) {
		String prefix = prefix(fileName);
		if (!StringUtils.isNullOrEmpty(prefix)) {
			String typeName = FileTypeUtils.getFileType(fileName);
			return prefix.equals(typeName.toUpperCase());
		}
		return false;
	}
	
	public static String getFileName(String filePath){
		return getFileName(new File(filePath));
	}
	
	public static String getFileName(File file){
		if(!file.exists() || file.isDirectory()){
			return null;
		}
		return file.getName();
	}

	/**
	 * 写入文件
	 * @param out 输出流
	 * @param fileName 文件名
	 */
	public static void write(OutputStream out,String fileName) throws Exception{
		InputStream in = new FileInputStream(fileName);
		int len = 0;
		byte[] buffer = new byte[1024];
		while ((len = in.read(buffer)) > 0) {
			out.write(buffer, 0, len);
		}
		in.close();
	}

	/**
	 * reponse 下载文件
	 * @param response http resposne 对象
	 * @param filePath 文件全路径,包括文件名
	 * @param fileName 客户端的显示文件名
	 * @throws Exception 
	 */
	public static void response(HttpServletResponse response, String filePath,String fileName) throws Exception {
		if(StringUtils.isNullOrEmpty(fileName)){
			fileName = getFileName(filePath);
		}
		setDownloadHeader(response,fileName);
		OutputStream out = response.getOutputStream();
		write(out,filePath);
	}
	
	public static void setDownloadHeader(HttpServletResponse response,String fileName) throws UnsupportedEncodingException{
		response.setContentType("application/octet-stream");
		response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));  
	}
	

}
