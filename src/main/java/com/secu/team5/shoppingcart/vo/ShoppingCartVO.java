package com.secu.team5.shoppingcart.vo;

import java.util.List;

import com.secu.team5.product.vo.ProductFileInfoVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ShoppingCartVO {
	//SHOPPING_CART columns
	private int scNum;
	private int uiNum;
	private int piNum;
	private int scAmount;
	private String scCredat;
	
	//etc
	private int piPrice;
	private String piName;
	private String piDesc;
	private String stiName;
	
	private List<ProductFileInfoVO> productFiles;
	
	

}
