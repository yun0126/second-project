package com.secu.team5.chat.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.secu.team5.chat.service.ChatUserInfoService;
import com.secu.team5.common.vo.ChatUserInfoVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ChatUserInfoController {
	
	private final ChatUserInfoService chatUserInfoService;
	
	
	@PostMapping("/api/react/join")
	public ChatUserInfoVO join(@RequestBody ChatUserInfoVO user) {
		log.info("user=>{}", user);
		return chatUserInfoService.join(user);
	}
	
	@PostMapping("/api/react/login")
	public ChatUserInfoVO login(@RequestBody ChatUserInfoVO user) {
		log.info("user=>{}", user);
		return chatUserInfoService.login(user);
		
		
	}
	
	
}
