package com.secu.team5.chat.service;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.secu.team5.chat.mapper.ChatMapper;
import com.secu.team5.chat.vo.ChatTableVO;
import com.secu.team5.user.mapper.UserInfoMapper;
import com.secu.team5.user.vo.UserInfoVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatService {
	
	private final ChatMapper chatMapper;
	private final UserInfoMapper userInfoMapper;
	
	//방번호는 1로 고정
	final int ROOM_NUM = 1;
	
	//세션을 이용해서 저장한다
	public int insertChat(ChatTableVO chat, Principal principal) {
		chat.setCtRoomnum(ROOM_NUM);
		UserInfoVO user = userInfoMapper.selectUserInfoById(principal.getName());
		chat.setUiNum(user.getUiNum());
		return chatMapper.insertChat(chat);
	}
	
	//채팅기록 전체 조회
	public List<ChatTableVO> selectChat(ChatTableVO chat){
		return chatMapper.selectChat(chat);
	}
	
}
