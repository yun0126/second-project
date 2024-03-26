package com.secu.team5.chat.service;

import org.springframework.stereotype.Service;

import com.secu.team5.chat.mapper.ChatUserInfoMapper;
import com.secu.team5.common.exception.AuthException;
import com.secu.team5.common.vo.ChatUserInfoVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatUserInfoService {

	private final ChatUserInfoMapper chatUserInfoMapper;

	public ChatUserInfoVO join(ChatUserInfoVO user) {
		if (chatUserInfoMapper.insertChatUserInfo(user) == 1) {
			ChatUserInfoVO newUser = chatUserInfoMapper.selectChatUserInfoById(user.getChiId());
			return newUser;
		}
		return null;
	}

	public ChatUserInfoVO login(ChatUserInfoVO user) {
		ChatUserInfoVO oldUser = chatUserInfoMapper.selectChatUserInfoById(user.getChiId());
		if (user.getChiPwd().equals(oldUser.getChiPwd())) {
			return oldUser;
		}
		throw new AuthException("로그인 오류");
	}
}
