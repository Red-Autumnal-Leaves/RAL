package com.ral.util.compressor;

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

/**
 * 
 * @author victor
 * @desc zip文件压缩解压辅助类
 */
public final class ZipCompressor {
	
	private ZipCompressor(){
		
	}

	/**
	 * 压缩文件/目录
	 * @param source 需要压缩为文件或者目录
	 * @param dest 压缩之后的文件全文件路径
	 */
	public static void compress(String source, String dest) {
		compress(new File(source),dest);
	}
	
	/**
	 * 压缩文件/目录
	 * @param file 需要压缩为文件对象
	 * @param dest 压缩之后的文件全文件路径
	 */
	public static void compress(File file, String dest) {
		Project project = new Project();
		FileSet fileSet = new FileSet();    
		fileSet.setProject(project);    
		fileSet.setDir(file);   
		Zip zip = new Zip();    
	    zip.setProject(project);    
	    zip.setDestFile(new File(dest));    
	    zip.addFileset(fileSet);    
	    zip.execute();    
	}
	

	/**
	 * 
	 * @param file 需要解压的文件对象zip
	 * @param dest 解压之后的文件路径
	 */
	public static void deCompress(File file, String dest){
		Expand expand = new Expand();  
	    expand.setDest(new File(dest));  
	    expand.setSrc(file);  
	    Project project = new Project();  
	    expand.setProject(project);  
	    expand.execute();  
	}
	
	/**
	 * 
	 * @param source 需要解压的zip文件路径
	 * @param dest 解压之后的文件路径
	 */
	public static void deCompress(String source, String dest){
		deCompress(new File(source),dest);
	}   
}
