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
	 * �ϴ��ļ�
	 * @param filepath ������ļ���
	 * @param filename ������ļ���(��������׺)
	 * @param types ������ļ�����
	 * @return 0:�ɹ� 1:δѡ���ļ� 2:ͼƬ�ϴ��쳣 3:���Ͳ�ƥ�� 4:�ļ�����
	 */
	public static int uploadFile(MultipartFile file, String filepath, String filename,HttpServletRequest request,String... types){
		//�ļ�����
		if (!filepath.startsWith("/")) {
			filepath = "/" + filepath;
		}
		if (!filepath.endsWith("/")) {
			filepath = filepath + "/";
		}
		String path = filepath;//�ļ���������·��
		String realPath = request.getSession().getServletContext().getRealPath(path);
		//�ϴ��ļ���ԭ��(���ϴ�ǰ���ļ�����)
		String originalFilename = null;
		List<String> allowType = Arrays.asList(types);
		//���ֻ���ϴ�һ���ļ�,��ֻ��ҪMultipartFile���ͽ����ļ�����,����������ʽָ��@RequestParamע��
		//������ϴ�����ļ�,��ô�����Ҫ��MultipartFile[]�����������ļ�,����Ҫָ��@RequestParamע��
		//�ϴ�����ļ�ʱ,ǰ̨���е�����<input type="file"/>��name��Ӧ����myfiles,����������myfiles�޷���ȡ�������ϴ����ļ�
		if(file == null || file.isEmpty()){
			return 1;	//δѡ���ļ�
		}else{
			originalFilename = file.getOriginalFilename();
			String type = originalFilename.substring(originalFilename.lastIndexOf("."));
			if (!allowType.contains(type)) {
				return 3; //���Ͳ�ƥ��
			} 
//			else if (file.getSize() > ConstUtil.getPhotoMaxSize()) {
//				return 4; //�ļ�����
//			} 
			else {
				try {
					String fileName = filename + type;
					FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, fileName));
					
					return 0;
				} catch (IOException e) {
					e.printStackTrace();
					return 2;	//�����쳣
				}
			}
		}
	}
	/**
	 * �ϴ��ļ�
	 * @param filepath ������ļ���
	 * @param filename ������ļ���(��������׺)
	 * @return 0:�ɹ� 1:δѡ���ļ� 2:ͼƬ�ϴ��쳣 3:���Ͳ�ƥ�� 4:�ļ�����
	 */
	public static int uploadImage(MultipartFile file, String filepath, String filename,HttpServletRequest request){
		return uploadFile(file, filepath, filename, request, ".jpg",".jpeg",".png",".gif",".bmp");
	}
	
	/**
	 * ɾ���ļ�
	 * @return 0:�ɹ� 1:�쳣
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
