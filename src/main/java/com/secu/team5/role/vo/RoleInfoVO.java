package com.secu.team5.role.vo;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//권한 확인을 위한 VO 요소가 나중에 추가될수도있음
public class RoleInfoVO implements GrantedAuthority{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//ROLE_INFO columns
	private String riCode;
	private String riName;
	private int riNum;
	//USER_INFO columns
	private int uiNum;
	

	@Override
	public String getAuthority() {
		
		return riCode;
	}
}
