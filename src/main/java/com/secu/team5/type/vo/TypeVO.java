package com.secu.team5.type.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//타입은 서브타입과 타입을 함께 사용하기 쉽게 속성값을 합쳐놓음 ProductVO참고
@Getter
@Setter
@ToString
public class TypeVO {
	//타입 테이블
	private int tiNum;
	private String tiName;
	private String tiCode;
	//서브타입 테이블
	private int StiNum;
	private String stiName;
	private String stiCode;
}
