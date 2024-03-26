package com.secu.team5.user.mapper;

import java.util.List;

import com.secu.team5.user.vo.UserInfoVO;

public interface UserInfoMapper {
	//회원 조회
	List<UserInfoVO> selectUserInfos(UserInfoVO user);
	//회원 조회
	List<UserInfoVO> selectUserInfosForChat(int uiNum);
	//특정 회원 조회
	UserInfoVO selectUserInfo(int uiNum);
	//아이디를 통한 회원 조회
	UserInfoVO selectUserInfoById(String uiId);
	//중복된 아이디가 있는지 확인
	int selectUserIdCnt(String uiId);
	//회원 가입
	int insertUserInfo(UserInfoVO user);
	//회원 수정
	int updateUserInfo(UserInfoVO user);
	//회원 등급 수정
	int updateuiGrade(UserInfoVO user);
	//회원 삭제
	int deleteUserInfo(int uiNum);
}
