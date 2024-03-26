package com.secu.team5.role.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.secu.team5.role.service.RoleInfoService;
import com.secu.team5.role.vo.RoleInfoVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RoleInfoController {
	
	private final RoleInfoService riService;
	
	//권한 전체 조회
	@GetMapping("/role-infos")
	public List<RoleInfoVO> getRoles(){
		return riService.selectRoles();
	}
	
	//권한 단일 조회
	@GetMapping("/role-infos/{uiNum}")
	public List<RoleInfoVO> getRole(@PathVariable int uiNum){
		return riService.selectRoleByuiNum(uiNum);
	}
	
	//권한 종류 조회
	@GetMapping("/role")
	public List<RoleInfoVO> selectRoleInfo(){
		return riService.selectRoleInfo();
	}
	
	//권한 추가
	@PostMapping("/role-infos")
	public int insertRole(@RequestBody RoleInfoVO roles) {
		return riService.insertRoleInfo(roles);
	}
	
	//권한 삭제
	@DeleteMapping("/role-infos")
	public int deleteRole(@RequestBody RoleInfoVO roles, Principal principal) {
		return riService.deleteRoleInfo(roles, principal);
	}
	
	
	
}
