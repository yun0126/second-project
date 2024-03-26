package com.secu.team5.common.util;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.secu.team5.user.vo.UserInfoVO;

public class SessionUtil {
	
	//세션에 있는 값을 가져와서 로그인 했는지 안했는지 판단을 한다
	
	
	public static Object getHttpSession() {
		if(SecurityContextHolder.getContext().getAuthentication() == null) {
			return "Non-session";
		}
		return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	public static boolean isLogin() {
		if(getHttpSession() instanceof UserInfoVO) {
			return true;
		}
		
		
		return false;
		
	}
}
