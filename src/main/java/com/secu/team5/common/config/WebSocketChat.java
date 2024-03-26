package com.secu.team5.common.config;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.secu.team5.common.vo.ChatVO;

import lombok.extern.slf4j.Slf4j;

@Component
@ServerEndpoint("/chat/{name}")
@Slf4j
public class WebSocketChat {
	// thread safety를 위해서 new를 사용하지 않는다.
	// 그래서 Collections를 사용한다
	private static Map<String, Session> sessionMap = Collections.synchronizedMap(new HashMap<>());
	private static Set<String> names = Collections.synchronizedSet(new HashSet<>());

	private ObjectMapper om = new ObjectMapper();
	
	//웹에서 웹소켓을통해 웹소켓을 열엇을때 사용되는 메소드
	@OnOpen
	public void open(Session session, EndpointConfig ec, @PathParam("name") String name) throws IOException {
		if (!names.add(name)) {
			ChatVO chat = new ChatVO();
			chat.setErrMsg("이미 중복된 이름입니다.");
			sendMsg(session, chat);
			session.close();
		}
		ChatVO chat = new ChatVO();
		chat.setName(name);
		chat.setMsg(name + "님이 접속하셧습니다");
		
		sessionMap.put(session.getId(), session);
		sendMsgAll(chat);
		log.info("open sessionMap =>{}", sessionMap);
	}
	
	//메세지 보내기 
	public void sendMsg(Session session, ChatVO chat) throws IOException {
		String json = om.writeValueAsString(chat);
		log.info("json => {}", json);
		sendMsg(session, json);
	}
	
	//세션맵에 들어가있는 세션 즉 접속자에게 전부 메세지를 돌리는 메소드
	public void sendMsgAll(ChatVO chat) throws IOException {
		Iterator<String> it = sessionMap.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			Session targetSession = sessionMap.get(key);
			sendMsg(targetSession, chat);
		}
	}
	//메세지 보내기
	private void sendMsg(Session session, String msg) throws IOException {
		log.info("msg => {}", msg);
		session.getBasicRemote().sendText(msg);

	}
	//웹소켓을통해 메세지를 받은경우 사용되는 메소드
	@OnMessage
	public void msg(String msg, Session session) throws IOException {

		Iterator<String> it = sessionMap.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
//			if(!key.equals(session.getId())){
			Session targetSession = sessionMap.get(key);
			targetSession.getBasicRemote().sendText(msg);
			// }
		}
	}

	@OnClose
	public void close(@PathParam("name") String name, Session session) {
		names.remove(name);
		log.info("close sessionMap =>{}", sessionMap);
		sessionMap.remove(session.getId());
	}
}
