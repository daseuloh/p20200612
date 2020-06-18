package com.example.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class MainInterceptor extends HandlerInterceptorAdapter{

	// /board/content?no=12
	// 원하는 페이지 전체에 현재 rul을 세션에 등록함
	@Override
	public void postHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		String path = request.getServletPath();
		String query = request.getQueryString();
		if(query == null) {
			request.getSession().setAttribute("CURRPAGE", 
					request.getContextPath() + path);
			System.out.println("INTERCEPTOR : " + request.getContextPath() + path);
		}
		else {
			request.getSession().setAttribute("CURRPAGE", 
					request.getContextPath() + path + "?" + query);
			System.out.println("INTERCEPTOR : " + request.getContextPath() + path+"?"+query);
		}
	}
	
}
