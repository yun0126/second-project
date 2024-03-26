package com.secu.team5.coupon.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CouponInfoVO {
	//COUPON_INFO columns
	private int cpiNum;
	private int cptiNum;
	private int uiNum;
	private Date cpiExpire;
	private Integer active;
	
	//USER_INFO columns(INNER JOIN해서 사용)
	private String uiName;
	private String uiGrade;
	
	//COUPON_TYPE_INFO columns(INNER JOIN해서 사용)
	private String cptiName;
	private String cptiDesc;
	private int cptiStatus;
	private String cptiCode;
	private int cptiDiscount;
	private int cptiDiscountRate;
	private int cptiCondition;
	
}
