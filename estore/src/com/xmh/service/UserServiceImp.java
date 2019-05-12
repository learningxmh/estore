package com.xmh.service;

import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.xmh.dao.UserDao;
import com.xmh.domain.User;
import com.xmh.factory.BasicFactory;
import com.xmh.utils.DaoUtils;

public class UserServiceImp implements UserService {

	private UserDao dao=BasicFactory.getFactory().getInstance(UserDao.class);
	public void regist(User user) {
		try {
			// 1.校验用户名是否已经存在
			if (dao.findUserByName(user.getUsername()) != null) {
				throw new RuntimeException("用户名已经存在！");
			}
			// 2.调用dao中的方法添加用户到数据库
			user.setRole("User");
			user.setState(0);
			user.setActivecode(UUID.randomUUID().toString());
			dao.addUser(user);
			
			// 3.发送激活邮件
			Properties prop=new Properties();
			prop.setProperty("mail.transport.protocol", "smtp");//协议
			prop.setProperty("mail.smtp.host", "localhost");//主机名
			prop.setProperty("mail.smtp.auth", "true");//是否开启权限控制
			prop.setProperty("mail.debug", "true");//如果设置为true则在发送邮件时会打印发送时的信息
			//创建程序到邮件服务器之间的一次会话
			Session session=Session.getInstance(prop);
			//获取邮件对象
			Message msg=new MimeMessage(session);
			msg.setFrom(new InternetAddress("发送端的地址aa@itheima.com"));
			msg.setRecipient(RecipientType.TO,new InternetAddress(user.getEmail()));
			msg.setSubject(user.getUsername()+",这是来自estore的一封激活邮件");
			msg.setText(user.getUsername()+"恭喜您成功注册estore，<a href='http://www.estore.com/ActiveServlet?activecode="+user.getActivecode()+">点我激活</a>");
			//找到邮递员
			Transport trans=session.getTransport();
			trans.connect("用户名aa@itheima.com","密码123");
			trans.sendMessage(msg, msg.getAllRecipients());
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("邮件发送异常");
		}
		
	}
	public void activeUser(String activecode) {
		//1、调用dao根据激活码查找用户
		User user=dao.findUserByActivecode(activecode);
		//2、如果找不到提示激活码无效
		if(user==null) {
			throw new RuntimeException("激活码不正确");
		}
		//3、如果用户已经激活过，提示不要重复激活
		if(user.getState()==1) {
			throw new RuntimeException("此用户已激活过，请不要重复激活！");
		}
		//4、如果没激活但是激活码已经超时，则提示，并删除用户
		if(System.currentTimeMillis()-user.getUpdatetime().getTime()>1000*3600*24) {
			dao.delete(user.getId());
			throw new RuntimeException("激活码已超时，请重新注册并在24小时内激活!");
		}
		//5、调用dao中的方法修改用户激活状态
		dao.updateState(user.getId(),1);
	}
	public User findUserByNameAndPsd(String username, String password) {
		return dao.findUserByNameAndPsd(username,password) ;
	}
	@Override
	public boolean hasName(String username) {
		return  dao.findUserByName(username)!=null;
	}

}
