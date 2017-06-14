package com.geek.control;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


public class YzmControl {
	
	@RequestMapping(value = "/dbAction_yzm", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String register(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException{
		
		String yzm = this.getStr();
		request.getSession().setAttribute("yzm", yzm);
		int width = 100;
		int height = 25;
		BufferedImage bi = new BufferedImage(width,height,1);//创建一张图片
		Graphics g = bi.getGraphics();//获得画笔
		g.setColor(Color.orange);//设置画笔颜色
		g.fillRect(0, 0, width, height);//填充背景颜色
		g.setColor(Color.red);//设置画笔颜色
		g.setFont(new Font("宋体",1,25));//设置字体
		g.drawString(yzm, 15, 22);//把字写入到图片中
		ServletOutputStream sos = response.getOutputStream();//使用流
		ImageIO.write(bi, "jpg", sos);//写入jsp页面
		g.dispose();
		sos.close();//关流
		
		 model.addAttribute("user", "");//传递数据 到jsp页面

		 return "success";//返回到success.jsp页面
	}
	
	private String getStr(){
		char[] ch={'1','2','3','4','5','6','7',
				'8','9','0','A','a','B','b','C','c',
				'D','d','E','e','F','f','G','g',
				'H','h','I','i','J','j','K','k',
				'L','l','M','m','N','n','O','o',
				'P','p','Q','q','R','r','S','s',
				'T','t','U','u','V','v','W','w',
				'X','x','Y','y','Z','z'};
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		for(int i=0;i<4;i++){
			int index = r.nextInt(ch.length);
			sb.append(ch[index]);
		}
		return sb.toString();
	}
}
