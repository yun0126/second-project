package com.secu.team5.coupon.mapper;

import java.util.List;

import com.secu.team5.coupon.vo.CouponInfoVO;

public interface CouponInfoMapper {

	int insertJoinCoupon(CouponInfoVO coupon);
	
	int insertGradeCoupon(CouponInfoVO coupon);
	
	List<CouponInfoVO> selectCouponByuiNum(int uiNum);
	
	boolean couponDoubleCheckForMonth(CouponInfoVO coupon);
	
	int updateUsedCoupon(CouponInfoVO coupon);
	
	int updateExpireCoupon(CouponInfoVO coupon);
}
