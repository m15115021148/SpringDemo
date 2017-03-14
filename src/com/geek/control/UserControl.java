package com.geek.control;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.geek.po.UserModel;
import com.geek.service.UserService;

@Controller
@RequestMapping("/userControl")
public class UserControl {
	@Resource
	private UserService userService;
	
	@RequestMapping("/register")
	public String register(HttpServletRequest request,Model model){
		String name = request.getParameter("name");
		String psw  = request.getParameter("password");
		System.out.print("name:"+name);
		UserModel user = new UserModel();
		user.setName(name);
		user.setPassword(psw);
		userService.register(user);
		model.addAttribute("user", user);
		return "success";
	}
	
	@RequestMapping("/check")
	public String check(HttpServletRequest request,Model model){
		String name = request.getParameter("name");
		System.out.print("name:"+name);
		List<UserModel> list = userService.findUserByUserName(name);
		model.addAttribute("list", list);
		return "success";
	}
}
