package com.secu.team5.common.vo;

import java.io.Serializable;

import javax.websocket.Session;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChatUserVO implements Serializable{
	//채팅사용을 위한 객체 세션과 이름을 사용한다
	private static final long serialVersionUID = 1L;
	private Session session;
	private String name;
}
