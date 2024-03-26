package com.secu.team5.chat.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//테이블컬럼이랑 같게 했음
@Getter
@Setter
@ToString
public class ChatTableVO {
	//CHAT_TABLE columns
	private int ctNum;
	private int uiNum;
	private String ctContent;
	private String ctCredat;
	private int ctRoomnum;
	private String uiId;
}
