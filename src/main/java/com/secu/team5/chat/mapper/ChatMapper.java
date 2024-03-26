package com.secu.team5.chat.mapper;

import java.util.List;

import com.secu.team5.chat.vo.ChatTableVO;

public interface ChatMapper {
	//저장
	int insertChat(ChatTableVO chat);
	//조회
	List<ChatTableVO> selectChat(ChatTableVO chat);
}
