package com.secu.team5.board.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardVO {
	
	//BOARD_INFO + BOARD_FILE_INFO columns, PageHelper
	private int biNum;
	private int uiNum;
	private String uiName;
	private int piNum;
	private String biTitle;
	private String biContent;
	private int biViews;
	private String credat;
	private String cretim;
	private String lmodat;
	private String lmotim;
	private int bfiNum;
	private String bfiName;
	private String bfiFilePath;
	private int bfiSort;
	private int page;
	private int PageSize;
}
