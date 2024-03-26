package com.secu.team5.qna.vo;


import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
//주석적어야할듯..
@Getter
@Setter
@ToString
public class BoardQnAFileInfoVO {

	private int bfiNum;
	private int bqiNum;
	private int rpiNum;
	private String bfiName;
	private String bfiFilePath;
	private MultipartFile file;
	private int bfiSort;
	private String status;
}
