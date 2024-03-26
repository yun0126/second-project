package com.secu.team5.coupon.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CouponTypeInfoVO {
	
	//COUPON_TYPE_INFO columns
	private int cptiNum;
	private String cptiName;
	private String cptiDesc;
	private int status;
	private String cptiCode;
	private Integer discount;
	private Integer discountRate;
	private int condition;

}
