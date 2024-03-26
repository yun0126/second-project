package com.secu.team5.qna.vo;




import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
//주석적어야할듯..
@Getter
@Setter
@ToString
public class BoardAdminInfoVO {
	private int bqiNum;		// 게시글 번호
	private int uiNum;		// 유저 번호
	private int piNum;		// 상품 번호
	private int tiNum;		// 상품 타입 번호
	private int rpiNum;		// 답변 번호
	private String bqiTitle;	// 게시글 제목
	private String bqiContent;	// 게시글 내용
	private String credat;		// 작성일
	private String cretim;		// 작성 시간
	private String lmodat;		// 수정일
	private String lmotim;		// 수정 시간
	private String uiName;		// 유저 이름
	private String piName;		// 상품 이름
	private String tiName;		// 상품 타입 이름
	private String rpiContent;	// 답변 내용
    //수정용변수
	private String bfiName;
	private String bfiFilePath;
	
	// 페이징 관련
	private int start;
	private int end;
	private int pageSize;
	private int page;
	private List<BoardQnAFileInfoVO> boardFiles;
}
