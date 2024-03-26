package com.secu.team5.common.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.secu.team5.common.provider.JWTTokenProvider;
import com.secu.team5.user.service.UserInfoService;
import com.secu.team5.user.vo.UserInfoVO;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthorizationFillter extends OncePerRequestFilter {

	private final JWTTokenProvider jwtProvider;
	private final UserInfoService userInfoService;
	
	//이필터는 토큰을 검증하기때문에 필요할때는 이필터를 사용하지 않게 해야한다
	//1 auth를 사용하던가
	//2 요청한곳이 같은사이트면(same-origin) 필터를 동작하지않게하던가
	/* --중요 http 헤더속 옵션들-- 
	 * 도메인 = 포트 면 same-site
	 * 도메인은 같은데 포트가 다르면 same-origin
	 * 도메인 자체가 다를때 cross-site
	 * 사용자가
	 * */
	
	//uri를 가져와서 필터를 거칠건지 판단하는 메소드 true면 필터를 안거친다
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
			//사용자가 URL를 브라우저를 통해 직접입력하면 sec-fetch-site 가 none 이나온다
			return "none".equals(request.getHeader("sec-fetch-site")) || "same-origin".equals(request.getHeader("sec-fetch-site"));
	
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (token == null || token.isEmpty()) {
			if (request.getCookies() != null) {
				Cookie[] cookies = request.getCookies();
				for (Cookie cookie : cookies) {
					if (HttpHeaders.AUTHORIZATION.equals(cookie.getName())) {
						token = cookie.getValue();
						break;
					}
				}
			}
		}
		
		if (token != null && !token.isEmpty()) {
			token = token.replace("Bearer ", "");
			Map<String, String> error = new HashMap<String, String>();
			String msg = null;
			try {
				if (jwtProvider.validation(token)) {
					String uiId = jwtProvider.getId(token);
					UserInfoVO user = (UserInfoVO) userInfoService.loadUserByUsername(uiId);
					user.setUiPwd(null);
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null,
							user.getAuthorities());
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
			} catch (ExpiredJwtException e) {
				msg = "토큰 유효기간 만료";
			} catch (UnsupportedJwtException e) {
				msg = "지원하지 않는 토큰입니다";
			} catch (SignatureException | MalformedJwtException e) {
				msg = "토큰 형식이 맞지 않습니다";
			}
			if (msg != null) {
				error.put("msg", msg);
				errJsonWrite(response, error, HttpStatus.FORBIDDEN);
			}
		}
		// 요청과 응답을 다음 필터로 넘기는 역할
		filterChain.doFilter(request, response);

	}

	private static void errJsonWrite(HttpServletResponse res, Map<String, String> error, HttpStatus status)
			throws IOException {
		res.setStatus(status.value());
		res.setContentType("application/json;charset=UTF-8");

		try {
			PrintWriter out = res.getWriter();
			JSONObject json = new JSONObject(error);
			out.print(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}





}
