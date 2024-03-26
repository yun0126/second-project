package com.secu.team5.board.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ckVO {
	//ck editor의 받는 요소들이 3가지라 이렇게 표현함
	private String fileName;
	private String url;
	private int uploaded;
}
