package com.cafe24.pjshop.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.pjshop.service.UserService;
import com.cafe24.pjshop.vo.UserVo;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("------->");
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		
		UserVo userVo = new UserVo();
//		userVo.setEmail(email);
//		userVo.setPassword(password);
//		
//		UserVo authUser = userService.getUser(userVo);
//		if(authUser == null) {
//			response.sendRedirect(request.getContextPath() + "/user/login");
//			return false;
//		}
		
		// session 처리
		HttpSession session = request.getSession(true);
//		session.setAttribute("authUser", authUser);
		response.sendRedirect( request.getContextPath() );

		return false;
	}

}