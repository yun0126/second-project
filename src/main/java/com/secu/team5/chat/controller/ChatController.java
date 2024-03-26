package com.secu.team5.chat.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.secu.team5.chat.service.ChatService;
import com.secu.team5.chat.vo.ChatTableVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//채팅기록을 저장하거나 마지막 채팅기록을 불러올때 사용할예정
@RestController
@RequiredArgsConstructor
@Slf4j
public class ChatController {
	
	private final ChatService chatService;
	
	//채팅 저장
	@PostMapping("/chat-infos")
	public int insertChat(@RequestBody ChatTableVO chat, Principal principal) {
		log.info("chat=>{}",chat);
		return chatService.insertChat(chat, principal);
	}
	//채팅 조회
	@GetMapping("/chat-infos")
	public List<ChatTableVO> selectChat(ChatTableVO chat){
		return chatService.selectChat(chat);
	}
}
