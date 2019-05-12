package com.xmh.service;

import com.xmh.domain.User;

public interface UserService {

	/*
	 * 注册用户
	 * @param user 封装了用户数据的userbean
	 * */
	void regist(User user);

	void activeUser(String activecode);

	User findUserByNameAndPsd(String username, String password);

	boolean hasName(String username);

}
