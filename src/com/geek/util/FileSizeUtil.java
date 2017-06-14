package com.geek.util;

import java.io.File;


public class FileSizeUtil {
	private static Long photoMaxSize;
	private static String shopPhotoSavePath;
	private static String businessPhotoSavePath;
	
	public static String getBusinessPhotoSavePath() {
		return businessPhotoSavePath;
	}

	public void setBusinessPhotoSavePath(String businessPhotoSavePath) {
		FileSizeUtil.businessPhotoSavePath = businessPhotoSavePath;
	}

	public static String getShopPhotoSavePath() {
		return shopPhotoSavePath;
	}

	public void setShopPhotoSavePath(String shopPhotoSavePath) {
		FileSizeUtil.shopPhotoSavePath = shopPhotoSavePath;
	}

	public static Long getPhotoMaxSize() {
		return photoMaxSize;
	}

	public void setPhotoMaxSize(Long photoMaxSize) {
		FileSizeUtil.photoMaxSize = photoMaxSize;
	}
	
	/**
	 * 清空目录中的内容
	 * @param fileDir
	 */
	public static void emptyDir(File fileDir) {
		if (fileDir.isDirectory()) {
            String[] children = fileDir.list();
            //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                emptyDir(new File(fileDir, children[i]));
            }
        }
	}
}
