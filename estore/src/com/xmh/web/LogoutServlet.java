package com.xmh.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.xmh.domain.User;
import com.xmh.factory.BasicFactory;
import com.xmh.service.UserService;

public class LogoutServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		if(request.getSession()!=null) {
			request.getSession().invalidate();
			//删除自动登陆cookie
			Cookie autologinc=new Cookie("autologin","");
			autologinc.setPath("/");
			autologinc.setMaxAge(0);
			response.addCookie(autologinc);
		}
		response.sendRedirect("/index.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
