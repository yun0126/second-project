package com.secu.team5.board.vo;




import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardInfoVO {
	//BOARD_INFO columns
	private int biNum;
	private int uiNum;
	private int piNum;
	private int tiNum;
	private String biTitle;
	private String biContent;
	private int biViews;
	private String credat;
	private String cretim;
	private String lmodat;
	private String lmotim;
	private String uiName;
	private String piName;
	private String tiName;
	private String uiId;
	
	//BOARD_FILE_INFO columns
	private String bfiName;
	private String bfiFilePath;
	
	//PageHelper
	private int start;
	private int end;
	private int pageSize;
	private int page;
	private List<BoardFileInfoVO> boardFiles;
}
