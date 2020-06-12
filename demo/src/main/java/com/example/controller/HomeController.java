package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//public class HomeController extends Controller{ 상속과 비슷한 개념이 어노테이션
public class HomeController {
	
	//로컬호스트 127.0.0.1:8080/ 로 접속
	@RequestMapping(value="/")
	public String home(HttpSession httpSession) {
		//세션 값 꺼내기
//		String userid
//		= (String)httpSession.getAttribute("SESSION_ID");
		return "index";

	}
	



}
