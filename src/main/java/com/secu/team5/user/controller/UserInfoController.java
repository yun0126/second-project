package com.secu.team5.user.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.secu.team5.user.service.UserInfoService;
import com.secu.team5.user.vo.UserInfoVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserInfoController {

	// 자 유저는 맵핑되어있는 것들이 많기때문에 삭제가 불가능한경우에는 뭐랑연결되어있는지 파악하고
	// 그 데이터를 같이 삭제하거나 연관된거 모두삭제하는 casecade 사용하도록한다.
	@Autowired
	private UserInfoService uiInfoService;

	// 유저 전부 조회
	@GetMapping("/user-infos")
	public List<UserInfoVO> selectUserInfos(UserInfoVO user) {
		return uiInfoService.selectUserInfos(user);
	}

	// 특정 유저 조회
	@GetMapping("/user-infos/{uiNum}")
	public UserInfoVO selectUserInfo(@PathVariable int uiNum) {
		return uiInfoService.selectUserInfo(uiNum);
	}

	// 마이페이지에서 회원정보조회를 위한 메소드
	@GetMapping("/user-info")
	public UserInfoVO selectUser(Principal principal) {
		return uiInfoService.getUser(principal);
	}

	// 회원가입
	@PostMapping("/join")
	public UserInfoVO join(@RequestBody UserInfoVO user) {
		log.info("user=>{}", user);
		int result = uiInfoService.join(user);
		if (result > 1) {
			user = uiInfoService.loadUserByUsername(user.getUiId());
			user.setUiId("회원 가입 완료");
		} else if (result == 0) {
			user.setUiId("중복된 아이디가 있습니다");
		}
		return user;
	}

	// 회원 수정
	@PatchMapping("/user-infos")
	public int update(@RequestBody UserInfoVO user) {
		return uiInfoService.userUpdate(user);
	}

	// 회원 등급 업데이트
	@PatchMapping("/user-grade")
	public int updateGrade(@RequestBody UserInfoVO user) {
		return uiInfoService.gradeUpdate(user);
	}

	// 관리자용 회원 정보 삭제
	@DeleteMapping("/user-infos/{uiNum}")
	public int delete(@PathVariable int uiNum) {
		return uiInfoService.deleteUserInfo(uiNum);
	}

	// 회원용 본인 계정 탈퇴
	@DeleteMapping("/user-infos/remove/{uiNum}")
	public int deleteUser(@PathVariable int uiNum, Principal principal, @RequestParam String password) {
		// 컨트롤러에서 비밀번호와 사용자 정보를 서비스로 전달
		return uiInfoService.deleteUser(uiNum, principal, password);
	}

	@GetMapping("/username")
	public String getUsername(Principal principal) {
		return principal.getName();
	}
}
