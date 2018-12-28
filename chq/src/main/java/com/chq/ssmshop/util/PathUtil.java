package com.chq.ssmshop.util;

import java.io.File;

public class PathUtil {

	/**
	 * 
	 * @return 这里返回图片目录的文件夹路径
	 */
	public static String getImageBasePath() {
		String os = System.getProperty("os.name");
		String seperator = File.pathSeparator;
		String basePath = "";
		if (os.toLowerCase().startsWith("win")) {
			basePath = "D:/ssmshop/imageResources/";
		} else {
			basePath = "/home/chq/java_workspace/ImageResources/";
		}
		basePath.replaceAll("/", seperator);
		return basePath;
	}

	/**
	 * 
	 * @return 这里返回存放店铺图片的文件夹路径
	 */
	public static String getShopImagePath() {
		return getImageBasePath() + "shop/";
	}

	public static String getProductThumbnailPath(Integer shopId, Integer productId) {
		return getShopImagePath() + shopId + "/" + productId + "/";
	}

	public static void main(String[] args) {
		System.out.println(System.getProperty("os.name"));
	}
}
