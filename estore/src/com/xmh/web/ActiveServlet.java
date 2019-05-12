package com.xmh.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xmh.domain.User;
import com.xmh.factory.BasicFactory;
import com.xmh.service.UserService;

public class ActiveServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService service=BasicFactory.getFactory().getInstance(UserService.class);
		//1、获取激活码
		String activecode=request.getParameter("activecode");
		//2、调用service中的方法激活
		service.activeUser(activecode);
		//3、激活成功，3秒后回到主页
		response.getWriter().write("激活成功，三秒后返回主页");
		response.setHeader("Refresh", "3;url=/index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
