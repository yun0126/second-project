package com.secu.team5.coupon.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.secu.team5.coupon.mapper.CouponTypeInfoMapper;
import com.secu.team5.coupon.vo.CouponTypeInfoVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CouponTypeInfoService {
	
	private final CouponTypeInfoMapper cptiMapper;
	
	public int createCouponTypeInfos(CouponTypeInfoVO type) {
		//쿠폰 코드 랜덤 부여
		String couponCode = "Coupon-" + UUID.randomUUID();
		type.setCptiCode(couponCode);
		
		//쿠폰 할인방식 = 할인금액일 시, 할인율을 null로 지정
		if (type.getDiscountRate() != null && type.getDiscountRate() != 0) {
		    type.setDiscount(null);
		//쿠폰 할인방식 = 할인율일 시, 할인금액을 null로 지정
		} else if (type.getDiscount() != null && type.getDiscount() != 0) {
		    type.setDiscountRate(null);
		}
		
		log.info("coupon=>{}",type);
		return cptiMapper.insertCouponTypeInfo(type);
	}

}
