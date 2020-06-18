package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.dao.MemberDAO;
import com.example.vo.MemberVO;

@Controller
@RequestMapping(value="/member")
public class MemberController {
	
	@Autowired
	private MemberDAO mDAO=null; //MemberDAO mDAO = new MemberDAO() 와 같은 말
	
	//ctrl+? => 주석문
	//ctrl+shift+f => 자동정렬
	
	
	
	@RequestMapping(value="/memberlist")
	public String memberlist(Model model) {
		List<MemberVO> list = mDAO.selectMemberList();

//		model.addAttribute("name", "가나다");
		model.addAttribute("list", list); //hashmap<key, value> Controller ->Views로 값 전달
		return "memberlist"; //memberlist.jsp를 표시하시오.
	}	
	
	
	
	
	
	
	
	
	@RequestMapping(value="/logout", method= RequestMethod.GET)
	public String logout(HttpSession httpSession) {
	httpSession.invalidate();
		return "redirect:/";
	}
	
	
	
	
	
	
	
	//로컬호스트 127.0.0.1:8080/
	@RequestMapping(value="/login", method= RequestMethod.GET)
	public String login() {
		return "login";
	}
	@RequestMapping(value="/login", method= RequestMethod.POST)
	public String loginpost(@ModelAttribute MemberVO obj, 
			HttpSession httpSession, HttpServletRequest request) {
		//DAO로 전달
		MemberVO obj1 = mDAO.selectMemberLogin(obj);
		if(obj1 !=null) { //로그인 성공
			httpSession.setAttribute("SESSION_ID", obj.getUserid());//obj 값은 이 메소드 나가면 소멸, 이 obj를 session_id에 담음, 보통 30분
			//즉, 로그아웃하려면 세션값 삭제하면 됨
			
			String backURL = (String) httpSession.getAttribute("CURRPAGE");
			
			return "redirect:" + backURL; //고정되면 안됨!! 마지막페이지로 가야됨.
		}
		//로그인 실패 /member/login GET방식으로 전송
		//redirect : jsp를 보여주는게 아니고 직접 크롬에 주소를 쳐서 들어가는 방식
		return "redirect:" + request.getContextPath() + "/member/login";
	}
	
	
	
	
	
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		return "join"; //join.jsp를 표시하시오.
	}	
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String joinpost(@ModelAttribute MemberVO obj) {	
		System.out.println(obj.toString());
		int ret = mDAO.insertMember(obj);
		
		if(ret > 0) { //회원가입 성공하면, 홈으로 보내기
			return  "redirect:/";
		}
		//DB로 전달해서 추가해야 함, 회원가입 실패시
		return "redirect:/member/join";
	}
	

}
