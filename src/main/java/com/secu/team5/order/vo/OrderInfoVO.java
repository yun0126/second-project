package com.secu.team5.order.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderInfoVO {
	//조회할때 join하는경우가 있어서 ODI TABLE이랑 OI의 컬럼을 같이 놓는다.
	//OI TABLE
	private int oiNum;
	private int uiNum;
	private int oiTotalPrice;
	private String oiAddress;
	private String oiMemo;
	private String oiCredat;
	private String oiCretim;
	private String oiMerchantUid; //OI_MERCHANT_UID 주문고유번호
	private String oiPay;    ///결제수단
	private String oiCoupon; //쿠폰사용유무

	private List<OrderDetailInfoVO> orderDetails;
	private String uiName;
	private String message;
	
	
}
