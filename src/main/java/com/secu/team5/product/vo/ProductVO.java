package com.secu.team5.product.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 리스트 화면을 보여주기 위한 VO 해당 VO는 productFileVO랑 ProductInfoVO를 합친것들이다
@Getter
@Setter
@ToString
public class ProductVO {
	// PRODUCT_FILE_INFO + PRODUCT_INFO, PageHelper
	private int piNum;
	private int uiNum;
	private String stiNum;
	private String piName;
	private String piDesc;
	private String piPrice;
	private int pfiNum;
	private String pfiName;
	private String pfiImgPath;
	private int pfiSort;
	private String stiName;
	private String tiName;
	private int page;
	private int PageSize;
}
