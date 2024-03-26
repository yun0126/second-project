package com.secu.team5.chat.mapper;

import com.secu.team5.common.vo.ChatUserInfoVO;

public interface ChatUserInfoMapper {
	
	int insertChatUserInfo(ChatUserInfoVO user);
	
	ChatUserInfoVO selectChatUserInfoById(String chiId);
	
}
