package com.secu.team5.common.controller;



import java.util.List;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.secu.team5.common.service.ReactChatService;
import com.secu.team5.common.util.DateUtil;
import com.secu.team5.common.vo.MessageVO;
import com.secu.team5.user.service.UserInfoService;
import com.secu.team5.user.vo.UserInfoVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ReactChatController {
	private final SimpMessagingTemplate smt;
	private final ReactChatService chatService;
	private final UserInfoService uiService;

	@MessageMapping("/react-chat/{uiNum}")
	public void chat(@DestinationVariable("uiNum") int uiNum, MessageVO message) {
		//내가 선택한 상대에게 메세지 보내기
		log.info("message.getCmiReceiveUiNum() =>{}", message.getCmiReceiveUiNum());
		message.setCmiSentTime(DateUtil.getToDate());
		smt.convertAndSend("/topic/chat/" + message.getCmiReceiveUiNum() , message);
		smt.convertAndSend("/topic/chat/" + message.getCmiSenderUiNum(), message);
		chatService.insertChatMessageInfo(message);
	}
	
	// ~~님이 입력중입니다 할려고했는데 미완성
	@MessageMapping("/chat-length/{uiNum}")
	public void chatlength(@DestinationVariable("uiNum") int uiNum, MessageVO message) {
		smt.convertAndSend("/topic/chat-length/" + message.getCmiReceiveUiNum() , message);
	}
	
	//채팅기록
	@GetMapping("/message-log/{cmiSenderUiNum}/{cmiReceiveUiNum}/{page}")
	public PageInfo<MessageVO> messageLog(
			@PathVariable("cmiSenderUiNum") int cmiSenderUiNum,
			@PathVariable("cmiReceiveUiNum") int cmiReceiveUiNum,
			@PathVariable("page") int page) {
		return chatService.selectChatMessageInfos(cmiSenderUiNum, cmiReceiveUiNum, page);
	}
	
	@PutMapping("/message-log")
	public boolean messageLog(@RequestBody MessageVO message){
		message.setCmiReceivedTime(DateUtil.getToDate());
		return chatService.updateChatMessageInfoReceivedTime(message);
	}

	@GetMapping("/chat-user-infos/{uiNum}")
	public List<UserInfoVO> selectUserInfosForChat(@PathVariable("uiNum") int uiNum) {
		return uiService.selectUserInfosForChat(uiNum);
	}
	
}
