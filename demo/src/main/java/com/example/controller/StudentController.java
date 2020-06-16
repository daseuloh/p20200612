package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Student;
import com.example.repository.StudentRepository;

@Controller
@RequestMapping(value = "/student")
public class StudentController {
	
	@Autowired
	private StudentRepository sRepository; //dao객체를 받아온것
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home() {
		return "/student/home"; 
	}
	

	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insertget() {
		return "/student/insert"; //views의 student폴더 밑에 insert.jsp표시
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insertpost(@ModelAttribute Student obj) {
		sRepository.save(obj); //인서트 완료
		
		return "redirect:/student/insert"; 
	}
	
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public String selectget(Model model) {
		//Iterable ==List
		Iterable<Student> list = sRepository.findAll();
//		Iterable<Student> list = sRepository.findByKorGreaterThan(80);
//		Iterable<Student> list = sRepository.selectStudentQuery(80);
		model.addAttribute("list",list);
	
		return "/student/select"; 
	}
	
	//select.jsp와 연결
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deletepost(@RequestParam("id")String id) {
		//엔티티에 필요한 정보 추가
		Student obj=new Student();
		obj.setId(id);
		sRepository.delete(obj); //삭제
		
		return "redirect:/student/home"; 
	}
	

	
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateget(@RequestParam("id")String id, Model model) {
		Student obj = sRepository.findById(id);
		model.addAttribute("obj", obj);
		return "/student/update";//views의 student폴더 밑에 insert.jsp표시
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updatepost(@ModelAttribute Student obj) {
		//기존 자료를 가져옴
		Student obj1 = sRepository.findById(obj.getId());
		
		//바꿀 자료값 저장함. 수정날짜로다시 받아옴
		obj.setDate(obj1.getDate());
		
		//obj1을 다시 저장함
		sRepository.save(obj);
		return "redirect:/student/home";//views의 student폴더 밑에 insert.jsp표시
	}
	
	
}




