package com.secu.team5.recent.vo;

import java.util.List;

import com.secu.team5.product.vo.ProductFileInfoVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RecentInfoVO {
	//RECENT_INFO columns
	private int riNum;
	private int uiNum;
	private int piNum;
	private String viewtime;
	private String piName;
	private String piPrice;
	private List<ProductFileInfoVO> productFiles;
}
