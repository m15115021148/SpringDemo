package com.geek.dao;

import java.util.List;

import com.geek.po.UserModel;


public interface UserDao {
	void registerUser(UserModel user);
	UserModel loginUser(UserModel user);
	UserModel findUserById(int id);
	List<UserModel> findUserByUserName(String name);
	
}
