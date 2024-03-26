package com.secu.team5.board.vo;


import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardFileInfoVO {
	//BOARD_FILE_INFO columns
	private int bfiNum;
	private int biNum;
	private String bfiName;
	private String bfiFilePath;
	private MultipartFile file;
	private int bfiSort;
	private String status;
}
