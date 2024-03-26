package com.secu.team5.common.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponsePageVO<T> {
	//페이징연습을 위한 VO
	private List<T> list;
	private int totalCnt;
	
}
