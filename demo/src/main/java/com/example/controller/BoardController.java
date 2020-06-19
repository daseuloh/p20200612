package com.example.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
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
import com.example.mapper.BoardMapper;
import com.example.vo.BoardVO;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

	@Autowired
	private BoardDAO bDAO = null; // 인터페이스 씀

	// DAO + XML 통합형
	@Autowired
	private BoardMapper boardMapper = null; // 인터페이스 씀

//	http://127.0.0.1:8080/board/update?no=14
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(HttpServletRequest request, Model model,
			@RequestParam(value = "no", defaultValue = "0") int no) {
		BoardVO vo = bDAO.selectBoardOne(no);

		String[] check = { "java", "jstl", "spring" }; // 추가한 form 태그에 적용될 코드
		vo.setTmp(check); // 추가한 form 태그에 적용될 코드

		model.addAttribute("vo", vo);

		List<String> selectList = new ArrayList<String>();// 추가한 form 태그에 적용될 코드
		selectList.add("java");// 추가한 form 태그에 적용될 코드
		selectList.add("jsp");// 추가한 form 태그에 적용될 코드
		selectList.add("spring");// 추가한 form 태그에 적용될 코드
		selectList.add("jstl");// 추가한 form 태그에 적용될 코드
		selectList.add("mybatis");// 추가한 form 태그에 적용될 코드
		model.addAttribute("slist", selectList);// 추가한 form 태그에 적용될 코드

		return "/board/update";
	}

//	http://127.0.0.1:8080/board/update?no=14
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(HttpServletRequest request, @ModelAttribute BoardVO obj, @RequestParam MultipartFile[] img)
			throws IOException {// 변수명 ==<input name ="img"

		// 이미지는 수동으로 obj에 추가함
		if (img != null) { // 이미지가 첨부되었다면
			for (MultipartFile one : img) {
				if (one.getSize() > 0) { // 첨부한 파일의 용량이 있느냐 (첨부하든 안하든 이미지사이즈는 1이 찍히기 때문)
//				if (!one.getOriginalFilename().equals("")) { // 파일명이 비어 있지 않다면
					obj.setBrd_img(one.getBytes());
				}
			}
		}
		// DAO로 obj값 전달하기
		bDAO.updateBoard(obj);
		return "redirect:" + request.getContextPath() + "/board/content?no=" + obj.getBrd_no();
	}

	// 127.0.0.1:8080/board/delete?no=13
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest request, @RequestParam(value = "no", defaultValue = "0") int no) {
		BoardVO obj = new BoardVO();


		System.out.println(no);
		obj.setBrd_no(no);
			
		// 이전방식(DAO, DAOIMPL,XML)
		int ret = bDAO.deleteBoard(obj);

		// 통합형 방식 사용하기 MAPPER
//		int ret = boardMapper.deleteBoard(obj);

		if (ret > 0) {// 성공시 목록화면
			// dao_>mapper거처 삭제하고 옴. 삭제하고 나면 리스트로 보내야함
			return "redirect:" + request.getContextPath() + "/board/list";
		}
		// 실패시 이전화면 즉, 상세화면으로
		// 127.0.0.1:8080/board/content?no=13
		
		return "redirect:" + request.getContextPath() + "/board/content?no=" + no;
	}

	// 127.0.0.1:8080/board/getimg?no=11
	@RequestMapping(value = "/getimg")
	public ResponseEntity<byte[]> getimg(@RequestParam("no") int no, HttpServletRequest request) {
		BoardVO obj = bDAO.selectBoardImg(no);
		try {
			if (obj.getBrd_img().length > 0) { // 이미지가 있음
				HttpHeaders header = new HttpHeaders();
				header.setContentType(MediaType.IMAGE_JPEG);
				// 원래 기본적으로 크롬은 우리가 return하면 html로 인식,
				// 즉 이미지같은 경우는 이게 이미지라고 말해줘야함.그게 header한테 , 내가 이미지jpeg보낼건데 이건 이미지로 인식해!라고하는게
				// setContentType
				ResponseEntity<byte[]> ret = new ResponseEntity<byte[]>(obj.getBrd_img(), header, HttpStatus.OK);
				// 이미지는 바이트타입으로 처리해야하니 명시하고, 이이미지 보낼거고, 헤더야 이거 이미지로 인식해라, hhtp상태 ok)
				return ret;
			}
			return null;
		} catch (Exception e) {// 이미지없을경우

			try {
				InputStream in = request.getServletContext().getResourceAsStream("/resources/img/default.jpg");
				// request.getServletContext().getResourceAsStream() == /src/main/webapp 까지 찾아줌
				HttpHeaders header = new HttpHeaders();
				header.setContentType(MediaType.IMAGE_JPEG);
				ResponseEntity<byte[]> ret = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), header, HttpStatus.OK);
				return ret;
			} catch (Exception e1) {
				return null;
			}
		}
	}

	//////////////////////////////////////////////////////////////

	@RequestMapping(value = "/insertbatch")
	public String insertbatch() {
		return "/board/insertbatch";
	}

	// 127.0.0.1:8080/board/insertbatch
	@RequestMapping(value = "/insertbatch", method = RequestMethod.POST)
	public String insertbatch(@RequestParam("brd_title[]") String[] brd_title,
			@RequestParam("brd_content[]") String[] brd_content, @RequestParam("brd_id[]") String[] brd_id) {

		List<BoardVO> list = new ArrayList<BoardVO>();
		for (int i = 0; i < brd_title.length; i++) {
			BoardVO obj = new BoardVO();
			obj.setBrd_title(brd_title[i]);
			obj.setBrd_content(brd_content[i]);
			obj.setBrd_id(brd_id[i]);

			list.add(obj); // VO가 brd_title배열 길이만큼 들어감
		}
		bDAO.insertBatch(list);

		return "redirect:/board/list";

	}

	//////////////////////////////////////////////////////////////
	@RequestMapping(value = "/content", method = RequestMethod.GET)
	public String content(Model model, HttpSession httpSession,
			@RequestParam(value = "no", defaultValue = "0", required = false) int no) {
		if (no == 0) {
			return "redirect:/board/list";
		}

		Integer chk = (Integer) httpSession.getAttribute("SESSION_BOARD_HIT_CHECK"); // getAttribute타입이 오브젝트타입이므로 형변환
																						// 해야함

		// 첫번째는 Integer 클래스를 리턴하기 때문에 산술 연산이 불가능
		// 두번째는 int 형을 리턴하기 때문에 산술 연산이 가능

		if (chk != null) { // chk가 int타입인데, null과 비교할 수 없으므로 integer형으로 변환
			if (chk == 1) {
				bDAO.updateHit(no);
			}
			httpSession.setAttribute("SESSION_BOARD_HIT_CHECK", 0);
		}

		BoardVO obj = bDAO.selectBoardOne(no);
		model.addAttribute("obj", obj); // jsp로 키가 obj인 것을 전달함

		int p = bDAO.selectBoardPrev(no); // 현재 글번호 넘어가면 이전글번호가 넘어옴
		model.addAttribute("prev", p);

		int n = bDAO.selectBoardNext(no);
		model.addAttribute("next", n);

		return "/board/content";
	}

	// 127.0.0.1:8080/board/list
	// 127.0.0.1:8080/board/list?page=55
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, HttpSession httpSeesion, HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "0", required = false) int page,
			@RequestParam(value = "text", defaultValue = "", required = false) String text) {

		if (page == 0) {
			return "redirect:" + request.getContextPath() + "/board/list?page=1"; // 입력안해도(page=0이라도) 자동으로 page=1이 완성됨
		}

		httpSeesion.setAttribute("SESSION_BOARD_HIT_CHECK", 1);

		// 목록
		HashMap<String, Object> map = new HashMap<String, Object>();
		// page
		// 1 -> 1, 10
		// 2 -> 11, 20
		map.put("start", page * 10 - 9); // 시작위치
		map.put("end", page * 10); // 종료위치
		map.put("text", text); // 검색어
		List<BoardVO> list = bDAO.selectBoard(map);
		model.addAttribute("list", list);

		// 게시물 개수
		int cnt = bDAO.countBoard(text); // 검색어를 넘겨줌
		// System.out.println((int) Math.ceil(cnt/10.0));
		model.addAttribute("cnt", (cnt - 1) / 10 + 1);

		return "/board/list";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insertBoard(HttpSession httpSession, Model model) {
		// 세션에서 로그인한 사용자의 아이디값을 가져옴
		String userid = (String) httpSession.getAttribute("SESSION_ID");
		if (userid == null) { // 아이디값이 없다면 로그인되지 않은 상태
			return "redirect:/member/login"; // 로그인 페이지로 이동
		}
		// 그렇지 않다면 게시판 글쓰기 화면 표시
		model.addAttribute("userid", userid); // model은 jsp로 값을 전달하는 것
		return "/board/insert";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insertBoardPost(@ModelAttribute BoardVO obj, @RequestParam MultipartFile[] imgs) throws IOException {
		if (imgs != null && imgs.length > 0) { // 이미지가 첨부되었다면
			for (MultipartFile one : imgs) {
				obj.setBrd_img(one.getBytes());
			}
		}
		// DAO로 obj값 전달하기
		System.out.println(obj.toString());
		int ret = bDAO.insertBoard(obj);

		if (ret > 0) { // 글쓰기 성공하면, 홈으로 보내기
			return "redirect:/";
		}

		// 글쓰기 실패시
		return "redirect:/board/insert";
	}

}
