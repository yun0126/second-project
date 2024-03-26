package com.secu.team5.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HTMLController {
	
	//홈페이지로 들어가는 url
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	//로그인과 회원가입 페이지
	@GetMapping("/user/**")
	public void user(){
		
	}
	
	
	//url패턴과 서버 내부 파일경로를 같게하여 편하게 찾아갈수있게 만든 메소드
	@GetMapping("/html/**")
	public void goPage() {
		
	}
	
	//url패턴과 서버 내부 파일경로를 같게하여 편하게 찾아갈수있게 만든 메소드
	@GetMapping("/fbs/**")
	public void fbsPage() {
		
	}
}
