package com.secu.team5.common.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.secu.team5.user.service.UserInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider{
	private final UserInfoService userService;
	private final PasswordEncoder passwordEncoder;

	//Ajax로 로그인 하는 과정 
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		//여기서 말하는 토큰 인스턴스는 사용자가 입력한 정보이다.
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
		//토큰의정보를 가져와 데이터베이스에있는 유저를 가져온다
		UserDetails user = userService.loadUserByUsername(token.getName());
		//토큰정보와 사용자가 입력한 비밀번호 값이 맞으면 사용자에게 토큰정보를 반환한다.
		if(passwordEncoder.matches(token.getCredentials().toString(), user.getPassword())) {
			return new UsernamePasswordAuthenticationToken(user, null,user.getAuthorities());
		}
		//맞지않으면 로그인 실패 반환
		throw new BadCredentialsException("login fail");
	}
	
	//AuthenticationProvider 에 있는 메소드 
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
