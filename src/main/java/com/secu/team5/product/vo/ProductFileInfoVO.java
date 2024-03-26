package com.secu.team5.product.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


//상품에 걸려있는 파일들
@Getter
@Setter
@ToString
public class ProductFileInfoVO {
	//PRODUCT_FILE_INFO columns
	private int pfiNum;
	private int piNum;
	private String pfiName;
	private String pfiImgPath;
	private int pfiSort;
	private String status;
	//파일첨부
	private MultipartFile file;
}
