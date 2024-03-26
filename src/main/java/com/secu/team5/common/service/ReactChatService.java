package com.secu.team5.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.secu.team5.common.mapper.ChatMessageInfoMapper;
import com.secu.team5.common.util.DateUtil;
import com.secu.team5.common.vo.MessageVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReactChatService {
	@Autowired
	private ChatMessageInfoMapper messageMapper;
	
	public int insertChatMessageInfo(MessageVO message) {
		message.setCmiSentTime(DateUtil.getToDate());
		message.setCmiReceivedTime(DateUtil.getToDate());
		log.info("message=>{}", message);
		
		return messageMapper.insertChatMessageInfo(message);
	}

	public PageInfo<MessageVO> selectChatMessageInfos(int cmiSenderUiNum, int cmiReceiveUiNum, int page) {
		PageHelper.startPage(page, 30);
		return PageInfo.of(messageMapper.selectChatMessageInfos(cmiSenderUiNum, cmiReceiveUiNum));
	}

	public boolean updateChatMessageInfoReceivedTime(MessageVO message) {
		return messageMapper.updateChatMessageInfoReceivedTime(message) > 0;
	}
}
