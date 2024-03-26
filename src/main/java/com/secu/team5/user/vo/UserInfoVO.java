package com.secu.team5.user.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//spring security 사용을 위해 userdetails 를 임플리먼트
@Getter
@Setter
@ToString
public class UserInfoVO implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//USER_INFO columns
	private int uiNum;
	private String uiId;
	private String uiPwd;
	private String uiName;
	private String uiEmail;
	private String uiPhone;
	private String uiBirth;
	private String uiGender;
	private String uiGrade;
	private String uiCredat;
	private String uiCretim;
	private String uiLmodat;
	private String uiLmotim;
	private String uiImgPath;
	private String sessionId;
	private String loginDate;
	private int riNum;
	private String token;
	private boolean login;
	private int unreadCnt;
	private Collection<? extends GrantedAuthority> authorities;
	
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return authorities;
	}
	@Override
	public String getPassword() {
		
		return uiPwd;
	}
	@Override
	public String getUsername() {
		
		return uiId;
	}
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}
	@Override
	public boolean isEnabled() {
		
		return true;
	}
	
}
