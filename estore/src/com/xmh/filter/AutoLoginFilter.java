package com.xmh.filter;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import com.xmh.domain.User;
import com.xmh.factory.BasicFactory;
import com.xmh.service.UserService;

public class AutoLoginFilter implements Filter {

	
	public void destroy() {
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse) response;
		//1、只有未登录的用户才自动登陆
		if(req.getSession(false)==null || req.getSession().getAttribute("user")==null) {
			//2、只有带了自动登陆cookie的用户才自动登陆
			Cookie [] cs=req.getCookies();
			Cookie findc=null;
			if(cs!=null) {
				for(Cookie c:cs) {
					if("autologin".equals(c.getName())) {
						findc=c;
						break;
					}
				}
			}
			if(findc!=null) {
				//3、只有自动登陆cookie中的用户名密码都正确才做自动登陆
				String v=URLDecoder.decode(findc.getValue(), "utf-8");
				String username=v.split(":")[0];
				String password=v.split(":")[1];
				UserService service=BasicFactory.getFactory().getInstance(UserService.class);
				User user=service.findUserByNameAndPsd(username, password);
				if(user!=null) {
					req.getSession().setAttribute("user", user);
				}
			}
		
		}
		
		
		//4、无论是否自动登陆都要放行
		chain.doFilter(request, response);
	}
	public void init(FilterConfig filterConfig) throws ServletException {
	
	}
	

}
