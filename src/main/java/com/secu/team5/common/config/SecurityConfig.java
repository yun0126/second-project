package com.secu.team5.common.config;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.secu.team5.common.filter.CustomAuthenticationFilter;
import com.secu.team5.common.filter.CustomAuthorizationFillter;
import com.secu.team5.common.provider.CustomAuthenticationProvider;
import com.secu.team5.common.provider.JWTTokenProvider;
import com.secu.team5.user.service.UserInfoService;

//스프링 Security를 사용한 초기 설정
@Configuration
public class SecurityConfig {
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private JWTTokenProvider jwtProvider;
	@Autowired
	private PasswordEncoder passwordEncoder;

 	//여기에있는 URL인경우에는 세션을 묻지도 따지지도 않는다 컨트롤러 URL을 여기다가 놓는경우 session을 파악할수 없으니 주의
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return web->{
			web.ignoring()
			.antMatchers("/css/**", "/js/**", "/imgs/**", "/resources/**", "/file/**","/type-infos", "/assets/**");
		};
	}
	
	//JWT로 로그인하는 과정에서 필터에 /api/login 으로 로그인 하였을때 사용하는 과정을 등록했음 - 여기서 로그인하는 과정의 첫번째 
	@Bean
	UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter() {
		CustomAuthenticationFilter filter = new CustomAuthenticationFilter(authenticationManager(),jwtProvider,objectMapper);
		filter.setFilterProcessesUrl("/api/login");
		filter.afterPropertiesSet();
		return filter;
	}
	
	//로그인하는 과정과 암호화한 방법을 어떤것을쓴건지 보여주는것
	@Bean
	AuthenticationProvider authenticationProvider() {
		return new CustomAuthenticationProvider(userInfoService, passwordEncoder);
	}
	//위의 클래스를 AuthenticationManager클래스로 변환시켜준것
	@Bean
	AuthenticationManager authenticationManager() {
		return new ProviderManager(authenticationProvider());
	}

	//권한 및 로그인 설정 등등 스프링 security의 옵션
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity hs) throws Exception {
		hs.authorizeRequests(req -> req.antMatchers("/api/login","/login", "/join", "/", "/user/login", "/user/join", "/product-infos/**", "/fbs/product/**", "/user/loginFail", "/user/denied", "/react/**", "/shopping-carts", "/comment-info/**", "/subtype-infos/**", "/comment-infos/**", "/coupon-infos/**", "/favicon.ico", "/api/react/login", "/api/react/join"
				, "/react-chat/**","/publish/**", "/chat-logs/**")
				.permitAll()
				.antMatchers("/html/**").hasRole("ADMIN")
				.antMatchers("/fbs/**").hasRole("USER")
				.antMatchers("/html/role_root/**").hasRole("ROOT")
				.anyRequest().authenticated())
		.formLogin(form-> form
				.loginPage("/user/login")
				.usernameParameter("uiId")
				.passwordParameter("uiPwd")
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/",true)
				.failureUrl("/user/loginFail"))
		.logout(log->log.logoutUrl("/logout")
				.logoutSuccessUrl("/"))
		.csrf(cs-> cs.disable())
		.exceptionHandling(hand -> hand.accessDeniedPage("/user/denied"))
		.userDetailsService(userInfoService)
		.cors(cors->cors.configurationSource(new CorsConfigurationSource() {
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				CorsConfiguration ccf = new CorsConfiguration();
				ccf.setAllowedOrigins(List.of("*"));
				ccf.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
				ccf.setAllowedHeaders(List.of("*"));
				ccf.setAllowCredentials(false);
				return ccf;
			}
		}))
		.addFilterBefore(new CustomAuthorizationFillter(jwtProvider, userInfoService), UsernamePasswordAuthenticationFilter.class);
		return hs.build();
	}
}
