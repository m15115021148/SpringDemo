package com.geek.util;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	/**
	 * 上传文件
	 * @param filepath 保存的文件夹
	 * @param filename 保存的文件名(不包含后缀)
	 * @param types 允许的文件类型
	 * @return 0:成功 1:未选择文件 2:图片上传异常 3:类型不匹配 4:文件过大
	 */
	public static int uploadFile(MultipartFile file, String filepath, String filename,HttpServletRequest request,String... types){
		//文件处理
		if (!filepath.startsWith("/")) {
			filepath = "/" + filepath;
		}
		if (!filepath.endsWith("/")) {
			filepath = filepath + "/";
		}
		String path = filepath;//文件保存的相对路径
		String realPath = request.getSession().getServletContext().getRealPath(path);
		//上传文件的原名(即上传前的文件名字)
		String originalFilename = null;
		List<String> allowType = Arrays.asList(types);
		//如果只是上传一个文件,则只需要MultipartFile类型接收文件即可,而且无需显式指定@RequestParam注解
		//如果想上传多个文件,那么这里就要用MultipartFile[]类型来接收文件,并且要指定@RequestParam注解
		//上传多个文件时,前台表单中的所有<input type="file"/>的name都应该是myfiles,否则参数里的myfiles无法获取到所有上传的文件
		if(file == null || file.isEmpty()){
			return 1;	//未选择文件
		}else{
			originalFilename = file.getOriginalFilename();
			String type = originalFilename.substring(originalFilename.lastIndexOf("."));
			if (!allowType.contains(type)) {
				return 3; //类型不匹配
			} 
//			else if (file.getSize() > ConstUtil.getPhotoMaxSize()) {
//				return 4; //文件过大
//			} 
			else {
				try {
					String fileName = filename + type;
					FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, fileName));
					
					return 0;
				} catch (IOException e) {
					e.printStackTrace();
					return 2;	//出现异常
				}
			}
		}
	}
	/**
	 * 上传文件
	 * @param filepath 保存的文件夹
	 * @param filename 保存的文件名(不包含后缀)
	 * @return 0:成功 1:未选择文件 2:图片上传异常 3:类型不匹配 4:文件过大
	 */
	public static int uploadImage(MultipartFile file, String filepath, String filename,HttpServletRequest request){
		return uploadFile(file, filepath, filename, request, ".jpg",".jpeg",".png",".gif",".bmp");
	}
	
	/**
	 * 删除文件
	 * @return 0:成功 1:异常
	 */
	public static int deleteFile(String filepath, String filename,HttpServletRequest request){
		if (!filepath.startsWith("/")) {
			filepath = "/" + filepath;
		}
		if (!filepath.endsWith("/")) {
			filepath = filepath + "/";
		}
		return deleteFile(filepath + filename, request);
	}
	
	public static int deleteFile(String filepath,HttpServletRequest request){
		try {
			String realPath = request.getSession().getServletContext().getRealPath(filepath);
			FileUtils.deleteDirectory(new File(realPath));
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		} 
	}
}
