package com.geek.service;

import java.util.List;

import com.geek.po.UserModel;

public interface UserService {
	void register(UserModel user);
	UserModel loginUser(UserModel user);
	UserModel findUserById(int id);
	List<UserModel> findUserByUserName(String name);
}
