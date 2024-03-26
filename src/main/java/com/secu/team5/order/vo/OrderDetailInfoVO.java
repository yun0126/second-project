package com.secu.team5.order.vo;

import java.util.List;

import com.secu.team5.product.vo.ProductFileInfoVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDetailInfoVO {
	//ORDER_DETAIL_INFO columns
	private int odiNum;
	private int uiNum;
	private int oiNum;
	private int piNum;
	private int odiAmount;
	private String odiCredat;
	private String odiCretim;
	private int odiPrice;
	
	//PRODUCT_INFO columns
	private String piName;
	private int piPrice;
	private int stiNum;
	private String piDesc;
	
	//TYPE_INFO columns
	private int tiNum;
	private String tiName;
	
	//SUB_TYPE_INFO columns
	private String stiName;
	
	//PageHelper
    private int start;
	private int end;
	private int pageSize;
	private int page;
	
	List<ProductFileInfoVO> productFiles;
}
