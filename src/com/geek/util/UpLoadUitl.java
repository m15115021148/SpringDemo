package com.geek.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public class UpLoadUitl {
	/**
	 * 上传图片
	 * @return 文件名
	 */
	public String photoUpload2(MultipartFile photo,Integer id, HttpServletRequest request, HttpServletResponse response){
		//FoodCateMiType MiType = foodCateMiTypeService.selectById(id.toString());
		String MaTypeName = id.toString();
		//文件处理
		String path = "/upload/news/img/" + MaTypeName;//文件保存的相对路径
		
		String realPath = request.getSession().getServletContext().getRealPath(path);
		//上传文件的原名(即上传前的文件名字)
		String originalFilename = null;
		List<String> allowType = Arrays.asList(".jpg",".jpeg",".png",".gif",".bmp");
		//如果只是上传一个文件,则只需要MultipartFile类型接收文件即可,而且无需显式指定@RequestParam注解
		//如果想上传多个文件,那么这里就要用MultipartFile[]类型来接收文件,并且要指定@RequestParam注解
		//上传多个文件时,前台表单中的所有<input type="file"/>的name都应该是myfiles,否则参数里的myfiles无法获取到所有上传的文件
		if(photo == null || photo.isEmpty()){
			return null;	//未选择文件
		}else{
			originalFilename = photo.getOriginalFilename();
			String type = originalFilename.substring(originalFilename.lastIndexOf("."));
			if (!allowType.contains(type)) {
				return null; //类型不匹配
			} else if (photo.getSize() > FileSizeUtil.getPhotoMaxSize()) {
				return null; //文件过大
			} else {
				try {
					String fileName = StringUtil.getUUID() + type;
					PicUtil.createMark(photo.getInputStream(), realPath,fileName, request);
					//FileUtils.copyInputStreamToFile(photo.getInputStream(), new File(realPath, fileName));
					return path.substring(path.startsWith("/") ? 1 : 0) + "/" + fileName;
				} catch (IOException e) {
					e.printStackTrace();
					return null;	//出现异常
				}
			}
		}
	}
	
}
