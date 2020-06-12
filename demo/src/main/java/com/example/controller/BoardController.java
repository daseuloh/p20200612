package com.example.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.dao.BoardDAO;
import com.example.vo.BoardVO;


@Controller
@RequestMapping(value = "/board")
public class BoardController {
	
	@Autowired
	private BoardDAO bDAO = null; //인터페이스 씀
	
	
	//127.0.0.1:800/board/getimg?no=10
	@RequestMapping(value="/getimg")
	public ResponseEntity<byte[]> getimg(@RequestParam("no") int no){
		BoardVO obj = bDAO.selectBoardImg(no);
		try {
			if(obj.getBrd_img().length > 0) {//이미지가 있음
				HttpHeaders header = new HttpHeaders();
				header.setContentType(MediaType.IMAGE_JPEG);
				ResponseEntity<byte[]> ret
					= new ResponseEntity<byte[]>(obj.getBrd_img(), header, HttpStatus.OK);
					return ret;
			} 
			return null;
		}
		catch(Exception e) {
			
			return null;
		}
	}
	
	//////////////////////////////////////////////////////////////
	
	@RequestMapping(value ="/insertbatch")
	public String insertbatch() {
		return "/board/insertbatch";
	}
	
	
	//127.0.0.1:8080/board/insertbatch
	@RequestMapping(value = "/insertbatch", method = RequestMethod.POST)
	public String insertbatch(
			@RequestParam("brd_title[]") String[] brd_title,
			@RequestParam("brd_content[]") String[] brd_content,
			@RequestParam("brd_id[]") String[] brd_id) {
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		for(int i = 0; i<brd_title.length; i++) {
			BoardVO obj = new BoardVO();
			obj.setBrd_title(brd_title[i]);
			obj.setBrd_content(brd_content[i]);
			obj.setBrd_id(brd_id[i]);


			list.add(obj); //VO가 brd_title배열 길이만큼 들어감
		}
		bDAO.insertBatch(list);
		
		return "redirect:/board/list";
		
	}
	
	
	
	
	
	
	
	
	
	
	
	//////////////////////////////////////////////////////////////
	@RequestMapping(value = "/content", method = RequestMethod.GET)
	public String content(Model model, HttpSession httpSession,
				@RequestParam (value="no", defaultValue = "0", required = false) int no) {
		if(no == 0) {
			return "redirect:/board/list";
		}
		
		Integer chk = (Integer)httpSession.getAttribute("SESSION_BOARD_HIT_CHECK"); //getAttribute타입이 오브젝트타입이므로 형변환 해야함
		
		//첫번째는 Integer 클래스를 리턴하기 때문에 산술 연산이 불가능
		//두번째는 int 형을 리턴하기 때문에 산술 연산이 가능 

		if(chk !=null) { //chk가 int타입인데, null과 비교할 수 없으므로 integer형으로 변환
			if(chk ==1) {
				bDAO.updateHit(no);
			}
			httpSession.setAttribute("SESSION_BOARD_HIT_CHECK", 0);
		}
		
		
		
		BoardVO obj = bDAO.selectBoardOne(no);
		model.addAttribute("obj", obj); //jsp로 키가 obj인 것을 전달함
		
		return "/board/content";
	}
	
	
	
	
	
	
	
	//127.0.0.1:8080/board/list
	//127.0.0.1:8080/board/list?page=55
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, HttpSession httpSeesion,
			@RequestParam (value="page", defaultValue = "1", required = false) int page) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		//page
		//1    -> 1, 10
		//2    -> 11, 20
		map.put("start", page*10-9);
		map.put("end", page*10);
		
		httpSeesion.setAttribute("SESSION_BOARD_HIT_CHECK", 1);
		
		
		
		//목록
		List<BoardVO> list = bDAO.selectBoard(map);
		
		//개수
		int cnt = bDAO.countBoard();
		
		model.addAttribute("list", list);
		
		//(int) Math.ceil(n/10.0)
		model.addAttribute("cnt", (cnt-1)/10+1);
		
		return "/board/list";
	}
	
	
	
	
	
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insertBoard(HttpSession httpSession, Model model) {
		//세션에서 로그인한 사용자의 아이디값을 가져옴
		String userid = (String)httpSession.getAttribute("SESSION_ID");
		if(userid == null) { //아이디값이 없다면 로그인되지 않은 상태
			return "redirect:/member/login"; //로그인 페이지로 이동
		}
		//그렇지 않다면 게시판 글쓰기 화면 표시
		model.addAttribute("userid", userid); //model은 jsp로 값을 전달하는 것
		return "/board/insert";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insertBoardPost(@ModelAttribute BoardVO obj, 
			@RequestParam MultipartFile[] imgs) throws IOException {
		if(imgs != null && imgs.length > 0) { //이미지가 첨부되었다면
			for(MultipartFile one : imgs) {
				obj.setBrd_img(one.getBytes());
			}
		}
		//DAO로 obj값 전달하기
		System.out.println(obj.toString());
		int ret = bDAO.insertBoard(obj);
		
		if(ret > 0) { //글쓰기 성공하면, 홈으로 보내기
			return  "redirect:/";
		}
		
		//글쓰기 실패시
		return "redirect:/board/insert";
	}

}




