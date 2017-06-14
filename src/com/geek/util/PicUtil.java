package com.geek.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class PicUtil {
	public static void createMark(InputStream inputStream,String savePath,String fileName,HttpServletRequest request) throws IOException {
		BufferedImage theImg=ImageIO.read(inputStream);
//       
//        ImageIcon markIcon=new ImageIcon(watermark); //Ҫ��ӵ�ˮӡͼ��
//        Image markImg =markIcon.getImage();
//       
//        int wid=markImg.getWidth(null); //ˮӡͼ����
//        int het= markImg.getHeight(null); //ˮӡͼ��߶�
//       
        //////////////////////////////////////////////////////////////////////
		BufferedImage bimage = createMark(theImg,request);
		
		File file = new File(savePath+"/"+fileName);
		if (!file.getParentFile().exists()) {
    		file.getParentFile().mkdirs();
		}
		if (!file.exists()) {
			file.createNewFile();
		}
        FileOutputStream out=new FileOutputStream(file);
        JPEGImageEncoder encoder =JPEGCodec.createJPEGEncoder(out);
        JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bimage);
        param.setQuality(50f, true); //ͼƬ����
        encoder.encode(bimage, param);
        out.close();
           
	}
	 public static boolean createMark(String filePath,String savePath,HttpServletRequest request) throws IOException {
		 	
	       /* ImageIcon imgIcon=new ImageIcon(filePath);
	        Image theImg =imgIcon.getImage();*/
	       // ImageIcon waterIcon=new ImageIcon(watermark);
	       // Image waterImg =waterIcon.getImage();
	       
	        ///////////////////////////////////////////////////////////////////////
	        File f = new File(filePath);
	        BufferedImage theImg=ImageIO.read(f);
	        BufferedImage bimage = createMark(theImg,request);
	        String picname = f.getName();//ȡ��ͼƬ��
	        
	        try{
	        FileOutputStream out=new FileOutputStream(savePath+"/"+picname);
	        JPEGImageEncoder encoder =JPEGCodec.createJPEGEncoder(out);
	        JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bimage);
	        param.setQuality(50f, true); //ͼƬ����
	        encoder.encode(bimage, param);
	        out.close();
	        }catch(Exception e){
	           
	                e.printStackTrace();
	                System.out.println("===========ʧ��");
	                return false;
	            
	            }
	            System.out.println("===========�ɹ�");
	            return true;
	          }
	 
	 public static void main(String[] args) throws IOException{
         //createMark("D:\\1.jpg","D:\\a");
     }
	 
	 private static BufferedImage createMark(BufferedImage theImg,HttpServletRequest request) throws IOException {
		 	BufferedImage icon=ImageIO.read(new File(request.getSession().getServletContext().getRealPath("/img/shuiyin_98.png")));
	        
//	       
//	        ImageIcon markIcon=new ImageIcon(watermark); //Ҫ��ӵ�ˮӡͼ��
//	        Image markImg =markIcon.getImage();
//	       
//	        int wid=markImg.getWidth(null); //ˮӡͼ����
//	        int het= markImg.getHeight(null); //ˮӡͼ��߶�
//	       
	        //////////////////////////////////////////////////////////////////////
	        int width=theImg.getWidth(null); //ԴͼƬ���
	        int height= theImg.getHeight(null); //ԴͼƬ�߶�
	        double widthIcon=icon.getWidth(null); //ԴͼƬ���
	        double heightIcon= icon.getHeight(null); //ԴͼƬ�߶�
	        double rate = widthIcon / heightIcon;
	        double drawW,drawH;
	        if (width < 500 || height < 500) {
				if (width < height) {
					drawW = 500;
					drawH = 500 * height / width;
				} else {
					drawH = 500;
					drawW = 500 * width / height;
				}
			} else {
				drawW = width;
				drawH = height;
			}
	        BufferedImage bimage = new BufferedImage((int)drawW,(int)drawH, BufferedImage.TYPE_INT_RGB);
	        //String str = "�ǻ۾���";
	        Graphics2D g=bimage.createGraphics( );
	        /*if (null != degree) {     
             // ����ˮӡ��ת     
             g.rotate(Math.toRadians(degree),     
                     (double) bimage.getWidth() / 2, (double) bimage     
                             .getHeight() / 2); 
             }*/
	        float alpha = 1f; // ͸����   
	        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));  
	        g.setColor(Color.black); //������ɫ
	        g.setBackground(Color.white);
	        int size = (int)(drawW  / 2.5);
	        if (size < 10) {
	        	size = 10;
	        }
	        g.setFont(new Font("����", Font.BOLD, size ));
	        g.drawImage(theImg, 0,0,(int) drawW,(int) drawH,null );
	        alpha = 1f; // ͸����   
	        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha)); 
	        g.drawImage(icon, (int)(drawW - size * (1 + 0.1 / rate)),(int)(drawH - size / rate * 1.1),size,(int)(size / rate),null );
	       // g.drawImage(waterImg, width-wid+5, height-het+5, null ); //���ͼ���м��������ֲ��� ���趨λ��
	        //g.drawString(str,width-(int)(size * 1.3)*str.length()+5,height-size+5); //�������
	        return bimage;
	}
}	
