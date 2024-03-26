package com.secu.team5.common.provider;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.secu.team5.user.service.UserInfoService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//JWT의 토큰을 발급하기 위한 클래스
@Component
@RequiredArgsConstructor
@Slf4j
public class JWTTokenProvider {
	// 유저 정보를 불러오기 위해서 userinfoService를 불러옴
	private final UserInfoService userDetailsService;

	// 토큰정보를 확인하기 위해서는 해당하는 문자열이 열쇠가 되는 것이다
	private final String tokenSecret = "adfasdfsadfasdfsa2131saf";

	// 토큰의 유효기간, 세션대신에 사용하는 느낌
	private final int tokenExpire = 1000 * 3600;

	// 리프레쉬 토큰의 유효기간.
	private final int refreshTokenExpire = 1000 * 3600;

	// 토큰 유효시간 가져오는 메소드
	public int getTokenExpire() {
		return tokenExpire;
	}

	// 토큰 터지는[유효기능이 없어지는] 시간이 언제인지 확인할때 쓰는 메소드
	private Date getJwtExpiretime(int tokenExpire) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MILLISECOND, tokenExpire);
		return c.getTime();
	}

	// tokenSecret 을 사용하여서 jwt에 담는것
	private Key getKey() {
		byte[] bytes = DatatypeConverter.parseBase64Binary(tokenSecret);
		return new SecretKeySpec(bytes, SignatureAlgorithm.HS256.getJcaName());
	}

	// jwt 자체를 뽑아오는것 알아볼수 없는 문자열로 되어있는데 jwt.io에서 확인가능 tokenSecret을 사용하면,..
	private String getJwt(Map<String, Object> claims, Date now, Date expireDate) {
		return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(expireDate)
				.signWith(SignatureAlgorithm.HS256, getKey()).compact();
	}

	// 토큰 생성하는 메소드
	public String generateToken(Authentication authentication) {
		Claims claims = Jwts.claims().setSubject(authentication.getName());
		return getJwt(claims, Calendar.getInstance().getTime(), getJwtExpiretime(tokenExpire));
	}

	// 리프레쉬 토큰을 생성하는 메소드
	public String generateRefreshToken(Authentication authentication) {
		Claims claims = Jwts.claims().setSubject(authentication.getName());
		String refreshToken = getJwt(claims, Calendar.getInstance().getTime(), getJwtExpiretime(refreshTokenExpire));
		return refreshToken;
	}

	// Authentication 객체를 반환하는 메소드
	public Authentication getAuthentication(String token) {
		String uiId = Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(token).getBody().getSubject();

		UserDetails userDetails = userDetailsService.loadUserByUsername(uiId);
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	// 토큰정보에 Bearer 이있으면 그부분은 잘라서 토큰정보만 보여주는 메소드
	public String resolveToken(HttpServletRequest req) {
		String token = req.getHeader("Authorization");
		if (token != null && token.startsWith("Bearer ")) {
			return token.substring(7);
		}
		return null;
	}

	// 토큰정보 검증 검증하는 도중에 기한이 만료되었거나 토큰이 오류가 있는경우 표시해준다
	public boolean validation(String token) {

		Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(token);
		return true;

	}

	// 토큰을 사용하여 아이디 정보를 불러온다
	public String getId(String token) {
		Claims claims = Jwts.parser().setSigningKey(getKey()).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
}
