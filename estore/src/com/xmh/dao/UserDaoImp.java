package com.xmh.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.xmh.domain.User;
import com.xmh.utils.DaoUtils;

public class UserDaoImp implements UserDao {

	public User findUserByName(String username) {
		String sql="select * from users where username=?";
		try {
			QueryRunner runner=new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanHandler<User>(User.class),username);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void addUser(User user) {
		String sql="insert into users values(null,?,?,?,?,?,?,?,null)";
		try {
			QueryRunner runner=new QueryRunner(DaoUtils.getSource());
			runner.update(sql,user.getUsername(),user.getPassword(),user.getNickname(),user.getEmail(),user.getRole(),user.getState(),user.getActivecode());
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public User findUserByActivecode(String activecode) {
		String sql="select * from users where activecode=?";
		try {
			QueryRunner runner=new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanHandler<User>(User.class),activecode);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void delete(int id) {
		String sql="delete from users where id=?";
		try {
			QueryRunner runner=new QueryRunner(DaoUtils.getSource());
			runner.update(sql,id);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	public void updateState(int id, int i) {
		String sql="update users set state=? where id=?";
		try {
			QueryRunner runner=new QueryRunner(DaoUtils.getSource());
			runner.update(sql,id,i);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public User findUserByNameAndPsd(String username, String password) {

		String sql="select * from users where username=? and password=?";
		try {
			QueryRunner runner=new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanHandler<User>(User.class),username,password);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
