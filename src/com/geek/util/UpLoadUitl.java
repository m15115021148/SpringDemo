package com.geek.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public class UpLoadUitl {
	/**
	 * �ϴ�ͼƬ
	 * @return �ļ���
	 */
	public String photoUpload2(MultipartFile photo,Integer id, HttpServletRequest request, HttpServletResponse response){
		//FoodCateMiType MiType = foodCateMiTypeService.selectById(id.toString());
		String MaTypeName = id.toString();
		//�ļ�����
		String path = "/upload/news/img/" + MaTypeName;//�ļ���������·��
		
		String realPath = request.getSession().getServletContext().getRealPath(path);
		//�ϴ��ļ���ԭ��(���ϴ�ǰ���ļ�����)
		String originalFilename = null;
		List<String> allowType = Arrays.asList(".jpg",".jpeg",".png",".gif",".bmp");
		//���ֻ���ϴ�һ���ļ�,��ֻ��ҪMultipartFile���ͽ����ļ�����,����������ʽָ��@RequestParamע��
		//������ϴ�����ļ�,��ô�����Ҫ��MultipartFile[]�����������ļ�,����Ҫָ��@RequestParamע��
		//�ϴ�����ļ�ʱ,ǰ̨���е�����<input type="file"/>��name��Ӧ����myfiles,����������myfiles�޷���ȡ�������ϴ����ļ�
		if(photo == null || photo.isEmpty()){
			return null;	//δѡ���ļ�
		}else{
			originalFilename = photo.getOriginalFilename();
			String type = originalFilename.substring(originalFilename.lastIndexOf("."));
			if (!allowType.contains(type)) {
				return null; //���Ͳ�ƥ��
			} else if (photo.getSize() > FileSizeUtil.getPhotoMaxSize()) {
				return null; //�ļ�����
			} else {
				try {
					String fileName = StringUtil.getUUID() + type;
					PicUtil.createMark(photo.getInputStream(), realPath,fileName, request);
					//FileUtils.copyInputStreamToFile(photo.getInputStream(), new File(realPath, fileName));
					return path.substring(path.startsWith("/") ? 1 : 0) + "/" + fileName;
				} catch (IOException e) {
					e.printStackTrace();
					return null;	//�����쳣
				}
			}
		}
	}
	
}
