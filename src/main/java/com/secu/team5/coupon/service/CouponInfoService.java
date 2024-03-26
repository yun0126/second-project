package com.secu.team5.coupon.service;

import java.security.Principal;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.secu.team5.coupon.mapper.CouponInfoMapper;
import com.secu.team5.coupon.vo.CouponInfoVO;
import com.secu.team5.user.mapper.UserInfoMapper;
import com.secu.team5.user.vo.UserInfoVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CouponInfoService {

	private final CouponInfoMapper ciMapper;
	
	private final UserInfoMapper uiMapper;
	
	// 회원가입 쿠폰 지급
	public int giveJoinCoupon(@RequestBody CouponInfoVO coupon) {
		// 종류(회원가입) 쿠폰 번호 부여
		coupon.setCptiNum(8);
		// active(1) : 사용가능한 쿠폰
		coupon.setActive(1);
		Date now = new java.sql.Date(System.currentTimeMillis());
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		// 쿠폰 만료기간 30일 후로 지정
		cal.add(Calendar.DAY_OF_MONTH, 30);
		Date expireDate = new java.sql.Date(cal.getTimeInMillis());
		coupon.setCpiExpire(expireDate);
		return ciMapper.insertJoinCoupon(coupon);
	}
	
	// 등급 쿠폰 지급
	public int giveGradeCoupon(@RequestBody CouponInfoVO coupon, Principal principal) {
		UserInfoVO user = new UserInfoVO();
		user = uiMapper.selectUserInfoById(principal.getName());
		coupon.setUiNum(user.getUiNum());
		coupon.setActive(1);
		// 해당 유저의 등급 값
		String grade = user.getUiGrade();
		
		// 쿠폰 중복 지급 방지 값
		boolean isCouponIssued = couponDoubleCheckForMonth(coupon, principal);
		if(!isCouponIssued) {
			if(grade.equals("BRONZE")) {
				coupon.setCptiNum(13);
			} else if(grade.equals("SILVER")) {
				coupon.setCptiNum(14);
			} else if(grade.equals("GOLD")) {
				coupon.setCptiNum(15);
			} else {
				coupon.setCptiNum(16);
			}
			
			Date now = new java.sql.Date(System.currentTimeMillis());
			Calendar cal = Calendar.getInstance();
			cal.setTime(now);
			cal.add(Calendar.DAY_OF_MONTH, 30);
			Date expireDate = new java.sql.Date(cal.getTimeInMillis());
			coupon.setCpiExpire(expireDate);
			// 등급쿠폰 지급
			return ciMapper.insertGradeCoupon(coupon);
		} else {
			// 등급쿠폰 미지급
			return 0;
		}
	}
	
	// 쿠폰 중복 지급 방지
	public boolean couponDoubleCheckForMonth(CouponInfoVO coupon, Principal principal) {
		UserInfoVO user = new UserInfoVO();
		user = uiMapper.selectUserInfoById(principal.getName());
		coupon.setUiNum(user.getUiNum());
		String grade = user.getUiGrade();
		log.info(grade);
		if(grade.equals("BRONZE")) {
			coupon.setCptiNum(13);
		} else if(grade.equals("SILVER")) {
			coupon.setCptiNum(14);
		} else if(grade.equals("GOLD")) {
			coupon.setCptiNum(15);
		} else {
			coupon.setCptiNum(16);
		}
        return ciMapper.couponDoubleCheckForMonth(coupon);
    }
	
	// 내 보유쿠폰 조회
	public List<CouponInfoVO> selectCouponByuiNum(int uiNum, Principal principal){
		try {
			principal.getName();
		} catch (Exception e) {
			return null;
		}
		UserInfoVO user = uiMapper.selectUserInfoById(principal.getName());
		if(user == null) {
			return null;
		}
		uiNum= user.getUiNum();
		return ciMapper.selectCouponByuiNum(uiNum);
	}
	
	// 결제에 사용한 쿠폰 active값 업데이트
	public int updateUsedCoupon(CouponInfoVO coupon, Principal principal) {
		UserInfoVO user = new UserInfoVO();
		user = uiMapper.selectUserInfoById(principal.getName());
		coupon.setUiNum(user.getUiNum());
		log.info("coupon=>{}",coupon);
		return ciMapper.updateUsedCoupon(coupon);
	}
	
	//매일 자정에 스케쥴러 실행
//	@Scheduled(cron = "0 0 0 * * *")
	//매분마다 스케쥴러 실행
//	@Scheduled(cron = "0 * * * * *")
	public void updateExpiredCoupons() {
		
		CouponInfoVO coupon = new CouponInfoVO();
		// 기한 만료 쿠폰 active 업데이트
        ciMapper.updateExpireCoupon(coupon);
        // active(3) : 기한 만료
        log.info("기한 만료 쿠폰 active(3) 업데이트");
	}
	
}
