package com.secu.team5.role.service;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.secu.team5.role.mapper.RoleInfoMapper;
import com.secu.team5.role.vo.RoleInfoVO;
import com.secu.team5.user.mapper.UserInfoMapper;
import com.secu.team5.user.vo.UserInfoVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleInfoService {
	
	private final RoleInfoMapper riMapper;
	private final UserInfoMapper uiMapper;
	
	
	//권한 조회
	public List<RoleInfoVO> selectRoles(){
		return riMapper.selectRoles();
	}
	
	//권한 조회 유저1
	public List<RoleInfoVO> selectRoleByuiNum(int uiNum){
		return riMapper.selectRolesByuiNum(uiNum);
	}
	
	//권한 종류 조회
	public List<RoleInfoVO> selectRoleInfo(){
		return riMapper.selectRoleInfos();
	}
	//권한 추가
	public int insertRoleInfo(RoleInfoVO role) {
		//권한 넣기전에 유저의 권한을 먼저 조회한다
		List<RoleInfoVO> userRoles = selectRoleByuiNum(role.getUiNum());
		//권한이 조회 된 경우
		if(userRoles != null) {
			for(RoleInfoVO usRole : userRoles) {
				//조회된 역할이 뽑아낸 역할과 뽑아낸 같으면 안넣기
				if(usRole.getRiNum() == role.getRiNum()) {
					return 0;
				}
			}
		}
		return riMapper.insertRolesByuiNum(role);
	}
	
	//권한 삭제
	public int deleteRoleInfo(RoleInfoVO role, Principal principal) {
		UserInfoVO user = uiMapper.selectUserInfoById(principal.getName());
		List<RoleInfoVO> roles = riMapper.selectRolesByuiNum(user.getUiNum());
		if(role.getRiNum() == 1) {
			return 0;
		}
		if(roles.size() == 1) {
			return 0;
		}
		return riMapper.deleteRoleMapWithDeleteUser(role);
	}
}
