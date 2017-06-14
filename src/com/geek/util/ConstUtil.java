package com.geek.util;

public class ConstUtil {
	private static Long photoMaxSize;
	private static Integer signGrade;
	private static String manageSiteUrl;
	private static String appSiteUrl;
	private static String businessPhoto;

	public static Long getPhotoMaxSize() {
		return photoMaxSize;
	}

	public void setPhotoMaxSize(Long photoMaxSize) {
		ConstUtil.photoMaxSize = photoMaxSize;
	}

	public static Integer getSignGrade() {
		return signGrade;
	}

	public void setSignGrade(Integer signGrade) {
		ConstUtil.signGrade = signGrade;
	}

	public static String getManageSiteUrl() {
		return manageSiteUrl;
	}

	public void setManageSiteUrl(String manageSiteUrl) {
		ConstUtil.manageSiteUrl = manageSiteUrl;
	}

	public static String getAppSiteUrl() {
		return appSiteUrl;
	}

	public void setAppSiteUrl(String appSiteUrl) {
		ConstUtil.appSiteUrl = appSiteUrl;
	}

	public static String getBusinessPhoto() {
		return businessPhoto;
	}

	public void setBusinessPhoto(String businessPhoto) {
		ConstUtil.businessPhoto = businessPhoto;
	}
}
