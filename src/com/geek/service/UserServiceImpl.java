package com.geek.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.geek.dao.UserDao;
import com.geek.po.UserModel;

@Service("userService")
public class UserServiceImpl implements UserService{
	@Resource
	private UserDao userDao;

	public void register(UserModel user) {
		userDao.registerUser(user);
	}

	public UserModel loginUser(UserModel user) {
		return userDao.loginUser(user);
	}

	public UserModel findUserById(int id) {
		return userDao.findUserById(id);
	}

	public List<UserModel> findUserByUserName(String name) {
		return userDao.findUserByUserName(name);
	}
	
}
