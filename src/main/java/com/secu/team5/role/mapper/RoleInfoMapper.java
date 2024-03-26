package com.secu.team5.role.mapper;

import java.util.List;

import com.secu.team5.role.vo.RoleInfoVO;

//사용자에게 권한부여와 삭제를 할수있게 해야겠지
public interface RoleInfoMapper {

	//뷰 권한조회
	List<RoleInfoVO> selectRolesByuiNum(int uiNum);
	//권한정보들
	List<RoleInfoVO> selectRoles();
	//권한종류 조회
	List<RoleInfoVO> selectRoleInfos();
	
	int insertRolesByuiNum(RoleInfoVO role);
	int deleteRoleMapWithDeleteUser(RoleInfoVO role);
	//회원삭제시 전부 역할 삭제
	int deleteRoleMapWithDeleteUser2(int uiNum);
	

}
