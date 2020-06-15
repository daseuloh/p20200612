package com.example.rest_controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.BoardDAO;
import com.example.vo.BoardVO;
import com.example.vo.OrderVO;

@RestController
@CrossOrigin("*")
public class RestBoardController {
	
	@Autowired
	private BoardDAO bDAO = null;
	
	@RequestMapping(value = "/rest/boardlist.json", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HashMap<String, Object> boardList(
			@RequestParam (value="page", defaultValue = "1", required = false) int page){
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", page*10-9);
		map.put("end", page*10);
		List<BoardVO> list = bDAO.selectBoard(map);
//		map.put("ret", list);
		
		HashMap<String, Object> map2 = new HashMap<String, Object>();
		map2.put("ret", list);
		
		return map2;
}

	
}
	
	
	
	
	