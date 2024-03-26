package com.secu.team5.user.service;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.secu.team5.coupon.service.CouponInfoService;
import com.secu.team5.coupon.vo.CouponInfoVO;
import com.secu.team5.role.mapper.RoleInfoMapper;
import com.secu.team5.role.vo.RoleInfoVO;
import com.secu.team5.user.mapper.UserInfoMapper;
import com.secu.team5.user.vo.UserInfoVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserInfoService implements UserDetailsService {
	// USER역할
	private final int DEFAULT_ROLE_NUM = 1;

	@Autowired
	private UserInfoMapper uiInfoMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleInfoMapper riMapper;

	@Autowired
	private CouponInfoService ciService;

	// 회원 조회
	public List<UserInfoVO> selectUserInfos(UserInfoVO user) {
		return uiInfoMapper.selectUserInfos(user);
	}
	public List<UserInfoVO> selectUserInfosForChat(int uiNum) {
		return uiInfoMapper.selectUserInfosForChat(uiNum);
	}

	// 특정회원 조회
	public UserInfoVO selectUserInfo(int uiNum) {
		return uiInfoMapper.selectUserInfo(uiNum);
	}

	// 마이페이지에서 회원정보 조회를 위한 메소드
	public UserInfoVO getUser(Principal principal) {
		UserInfoVO user = uiInfoMapper.selectUserInfoById(principal.getName());
		return user;
	}

	// 회원가입할때 역할도 같이 넣음
	public int join(UserInfoVO user) {
		if (uiInfoMapper.selectUserInfoById(user.getUiId()) != null) {
			return 0;
		}
		user.setUiBirth(user.getUiBirth().replaceAll("-", ""));
		CouponInfoVO coupon = new CouponInfoVO();
		RoleInfoVO role = new RoleInfoVO();
		user.setUiPwd(passwordEncoder.encode(user.getUiPwd()));
		int result = 0;
		result += uiInfoMapper.insertUserInfo(user);
		log.info("user", user);
		role.setRiNum(DEFAULT_ROLE_NUM);
		role.setUiNum(user.getUiNum());
		// 역할 추가
		result += riMapper.insertRolesByuiNum(role);
		coupon.setUiNum(user.getUiNum());
		// 회원가입 추가
		result += ciService.giveJoinCoupon(coupon);
		return result;
	}

	// 회원 수정
	public int userUpdate(UserInfoVO user) {
		log.info("user=>{}", user);
		if (user.getUiPwd() == null || user.getUiPwd() == "") {
			user.setUiPwd(uiInfoMapper.selectUserInfo(user.getUiNum()).getUiPwd());
		} else {
			user.setUiPwd(passwordEncoder.encode(user.getUiPwd()));
		}
		return uiInfoMapper.updateUserInfo(user);
	}

	// 회원 등급 수정
	public int gradeUpdate(UserInfoVO user) {
		return uiInfoMapper.updateuiGrade(user);
	}

	// 관리자용 회원 삭제 - 이거삭제하면 DB와 관련된것들도 사라지니까 주의
	public int deleteUserInfo(int uiNum) {

		int result = riMapper.deleteRoleMapWithDeleteUser2(uiNum);
		result += uiInfoMapper.deleteUserInfo(uiNum);
		return result;
	}

	// 회원용 탈퇴 - 비밀번호 비교 로직 포함
	public int deleteUser(int uiNum, Principal principal, String password) {
		// 접속되어있는 사용자 정보 가져오기
		UserInfoVO user = getUser(principal);

		// 입력한 비밀번호와 사용자의 저장된 비밀번호가 일치하는지 확인
		if (passwordEncoder.matches(password, user.getUiPwd())) {
			// 비밀번호가 일치하면 관련된 데이터 삭제 진행
			return uiInfoMapper.deleteUserInfo(uiNum);
		} else {
			// 비밀번호가 일치하지 않는 경우
			log.info("Entered Password: {}", password);
			return -1;
		}
	}

	// 현재 로그인 중인 사용자의 uiNum 반환 비밀번호 체크할때 유저정보 뽑을때 사용
	public Integer getUserNum(Principal principal) {
		UserInfoVO user = uiInfoMapper.selectUserInfoById(principal.getName());
		return user != null ? user.getUiNum() : null;
	}

	// 로그인할때 사용하는것
	@Override
	public UserInfoVO loadUserByUsername(String uiId) throws UsernameNotFoundException {
		UserInfoVO user = uiInfoMapper.selectUserInfoById(uiId);
		if (user == null) {
			throw new UsernameNotFoundException("Error");
		}
		user.setAuthorities(riMapper.selectRolesByuiNum(user.getUiNum()));
		return user;
	}

}
