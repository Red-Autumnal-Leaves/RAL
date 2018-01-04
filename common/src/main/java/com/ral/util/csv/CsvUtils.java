package com.ral.util.csv;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.List;

import au.com.bytecode.opencsv.CSVWriter;

/**
 * 
 * @author victor
 * @desc csv 辅助类
 */
public class CsvUtils {

	private CsvUtils() {
	}

	/***
	 * 导出csv
	 * @param content
	 * @param file
	 * @throws IOException
	 */
	public static void export(List<String[]> content, File file) throws IOException {
		if (file == null) return;
		OutputStream os = new FileOutputStream(file, true);
		os.write(239); // 0xEF
		os.write(187); // 0xBB
		os.write(191); // 0xBF
		OutputStreamWriter osw = new OutputStreamWriter(os, Charset.forName("UTF-8"));
		CSVWriter writer = new CSVWriter(osw, ',');
		writer.writeAll(content);
		writer.close();
	}

	/***
	 * 导出csv
	 * @param content
	 * @param file
	 * @param separator
	 * @throws IOException
	 */
	public static void export(List<String[]> content, File file, char separator) throws IOException {
		if (file == null) return;
		OutputStream os = new FileOutputStream(file, true);
		os.write(239); // 0xEF
		os.write(187); // 0xBB
		os.write(191); // 0xBF
		OutputStreamWriter osw = new OutputStreamWriter(os, Charset.forName("UTF-8"));
		CSVWriter writer = new CSVWriter(osw, separator);
		writer.writeAll(content);
		writer.close();
	}

	/***
	 * 导出csv文件
	 * @param content
	 * @param filePath
	 * @throws IOException
	 */
	public static void export(List<String[]> content, String filePath) throws IOException {
		File file = new File(filePath);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		OutputStream os = new FileOutputStream(filePath, true);
		os.write(239); // 0xEF
		os.write(187); // 0xBB
		os.write(191); // 0xBF
		OutputStreamWriter osw = new OutputStreamWriter(os, Charset.forName("UTF-8"));
		CSVWriter writer = new CSVWriter(osw, ',');
		writer.writeAll(content);
		writer.close();

	}

	/***
	 * 导出csv
	 * 
	 * @param content  导出内容
	 * @param filePath 文件路径
	 * @param separator 分隔符
	 * @throws IOException
	 */
	public static void export(List<String[]> content, String filePath, char separator) throws IOException {
		File file = new File(filePath);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		OutputStream os = new FileOutputStream(filePath, true);
		os.write(239); // 0xEF
		os.write(187); // 0xBB
		os.write(191); // 0xBF
		OutputStreamWriter osw = new OutputStreamWriter(os, Charset.forName("UTF-8"));
		CSVWriter writer = new CSVWriter(osw, separator);
		writer.writeAll(content);
		writer.close();
	}

}
