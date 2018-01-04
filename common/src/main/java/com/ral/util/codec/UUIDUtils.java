package com.ral.util.codec;

/**
 * 
 * @author victor
 * @desc uuid生成辅助类 
 */
public class UUIDUtils {

	/**
	 * 
	 * @return
	 * @desc 生成UUID 去掉 '-'
	 */
	public static String uuid(){
		return java.util.UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static void main(String[] args) {
		System.out.println(uuid());
	}
	
}
