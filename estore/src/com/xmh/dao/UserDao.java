package com.xmh.dao;

import java.util.Hashtable;

import com.xmh.domain.User;

public interface UserDao {

	/*
	 * 根据用户名查找用户
	 * @param username 用户名
	 * @return 查找到的用户，如果找不到返回null
	 * */
	User findUserByName(String username);

	/*
	 * 添加用户
	 * @param user 封装了用户信息的bean
	 * */
	void addUser(User user);
	/*
	 * 根据激活码查找用户
	 * @param activecode 激活码
	 * @return 查找到的用户，如果找不到返回null
	 * */
	User findUserByActivecode(String activecode);

	/*
	 * 根据id删除用户
	 * @param id 要删除的用户id
	 * 
	 * */
	void delete(int id);
	/*
	 * 修改指定id用户的状态
	 * @param id 用户id
	 * @param i  要更新的用户状态
	 * */
	void updateState(int id, int i);
	/*
	 * 根据用户名和密码查找用户
	 * @param username 用户名
	 * @param password 密码
	 * @return 查找到的用户，如果找不到返回null
	 * */
	User findUserByNameAndPsd(String username, String password);


}
