package com.secu.team5.product.vo;


import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


//상품정보 데이터베이스를 기반으로한 상품 VO
@Getter
@Setter
@ToString
public class ProductInfoVO {
	//PRODUCT_INFO columns
	private int piNum;
	private int uiNum;
	private int stiNum;
	private int tiNum;
	private String piName;
	private String piDesc;
	private String piPrice;
	private List<ProductFileInfoVO> productFiles;
}
