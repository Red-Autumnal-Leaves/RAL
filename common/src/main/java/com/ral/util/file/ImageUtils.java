package com.ral.util.file;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

/**
 * 
 * @author victor
 * @desc 图片工具类,使用第三方工具，每次操作图片均需要重新读取图片，如果一次同时进行多种操作，建议添加方法，提高效率
 */
public final class ImageUtils {

	private static Logger logger = Logger.getLogger(ImageUtils.class);
	
	private ImageUtils(){
	}
	
	/**
	 * 等比缩小或者放大图片,会改变图片空间大小和尺寸大小
	 * @param sourceFileName 图片文件全路径
	 * @param newFileName 新图片文件路径
	 * @param ratio 比例  大于1放大，小于1缩小
	 * 
	 */
	public static void scale(String sourceFileName,String newFileName,double ratio){
		try {
			Thumbnails.of(sourceFileName).scale(ratio).toFile(newFileName);
		} catch (IOException e) {
			e.printStackTrace();
			logger.debug("scale image("+sourceFileName+") error.",e);
		}
	}
	
	/**
	 * 指定尺寸进行缩放.<br/>
	 * @param sourceFileName 图片文件全路径
	 * @param newFileName 新图片文件路径
	 * @param width 宽 单位像素
	 * @param height 高 像素
	 * @keepAspectRatio 是否等比,等比指定尺寸并不一定有效，不等比将拉伸图片
	 */
	public static void scale(String sourceFileName,String newFileName,int width,int height,boolean keepAspectRatio){
		try {
			Thumbnails.of(sourceFileName).size(width, height).keepAspectRatio(keepAspectRatio).toFile(newFileName);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("scale image("+sourceFileName+") error.",e);
		} 
	}
	
	
	/**
	 * 旋转图片
	 * @param sourceFileName 图片文件全路径
	 * @param newFileName 新图片文件路径
	 * @param rotate 旋转度数 ,正数：顺时针 负数：逆时针 
	 */
	public static void rotate(String sourceFileName,String newFileName,int rotate){
		try {
			Thumbnails.of(sourceFileName).rotate(rotate).toFile(newFileName);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("scale rotate("+sourceFileName+") error.",e);
		}   
	}
	
	/**
	 * 添加水印
	 * @param sourceFileName 图片文件全路径
	 * @param watermarkFileName 水印图片路径
	 * @param position 水印位置，枚举
	 * @param newFileName 新图片文件路径
	 */
	public static void watermark(String sourceFileName,String watermarkFileName,Positions position,String newFileName){
		watermark(sourceFileName,watermarkFileName,position,1,newFileName);
	}
	
	/**
	 * 添加水印
	 * @param sourceFileName 图片文件全路径
	 * @param watermarkFileName 水印图片路径
	 * @param position 水印位置，枚举
	 * @param quality 水印透明度
	 * @param newFileName 新图片文件路径
	 */
	public static void watermark(String sourceFileName,String watermarkFileName,Positions position,double quality,String newFileName){
		try {
			BufferedImage image = ImageIO.read(new File(sourceFileName));
			Thumbnails.of(image).size(image.getWidth(), image.getHeight())
			.watermark(position, ImageIO.read(new File(watermarkFileName)),(float) quality)   
			.toFile(newFileName);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("scale watermark("+sourceFileName+") error.",e);
		}  
	}
	
	/**
	 * 转换图片格式
	 * @param sourceFileName 原始图片
	 * @param format 格式，如gif jpg png
	 * @param newFileName 新文件路径
	 */
	public static void format(String sourceFileName,String format,String newFileName){
		try {
			BufferedImage image = ImageIO.read(new File(sourceFileName));
			Thumbnails.of(image).size(image.getWidth(), image.getHeight()).outputFormat(format).toFile(newFileName);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("scale format("+sourceFileName+") error.",e);
		}   
	}
	
	
	
	
}
