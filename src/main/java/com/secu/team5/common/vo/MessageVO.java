package com.secu.team5.common.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MessageVO {

	private String cmiMessage;
	private String cmiSentTime;
	private String cmiReceivedTime;
	private String cmiSender;
	private String cmiDirection;
	private String cmiPosition;
	private String cmiType;
	private String cmiPayload;
	private int cmiSenderUiNum;
	private int cmiReceiveUiNum;
	private int page;
	private int unreadCnt;
}
