package com.ral.util.codec;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

	public static String hash(String source) {
		StringBuilder sb = new StringBuilder();
		if (source != null) {
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				byte bytes[] = md.digest(source.getBytes());
				for (int i = 0; i < bytes.length; i++) {
					String str = Integer.toHexString(bytes[i] & 0xFF);
					if (str.length() == 1) {
						str += "F";
					}
					sb.append(str);
				}
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		return sb.toString().toUpperCase();
	}
	
	public static boolean check(String source,String md5){
		return MD5Utils.hash(source).equals(md5);
	}
	
	public static void main(String[] args) {
		System.out.println(MD5Utils.hash("admin"));
	}
}
