package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dao.ItemDAO;
import com.example.vo.ItemVO;

@Controller
@RequestMapping(value="/item")
public class ItemController {
	
	@Autowired
	private ItemDAO iDAO=null;
	
	@RequestMapping(value = "/list")
	public String itemlist(Model model) {
		List<ItemVO> list = iDAO.selectItemList();
		
		model.addAttribute("list", list);
		return "/item/list";
		
		
	}
	

}
