package com.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.mapper.User1Mapper;
import com.example.vo.User1VO;

@Controller
@RequestMapping("/security")
public class SecurityController {
	
	@Autowired
	private User1Mapper user1Mapper;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home1(Model model) {
		return "/security/home";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "/security/join";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute User1VO obj,
			HttpServletRequest request) {
		System.out.println(obj.toString());
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		//a라는 문자가 들어오면 =>fpsdof32l4jpdfji324asdlfjsp   +salt(랜덤값)
		String str1 = passwordEncoder.encode(  obj.getPassword() );
		obj.setPassword(str1);
		
		user1Mapper.insertMember(obj);
		
		return "redirect:"+ request.getContextPath() + "/security/home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "/security/login";
	}
	
	@RequestMapping(value = "/page403", method = RequestMethod.GET)
	public String page403() {
		return "/security/page403";
	}
	
	
	
	
	
	
	
}