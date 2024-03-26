package com.secu.team5.common.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.secu.team5.common.exception.AuthException;

@ControllerAdvice
public class ControllerExceptionAdvice {

	@ExceptionHandler
	public ResponseEntity<String> authException(AuthException exception){
		ResponseEntity<String> res = new ResponseEntity<String>("login에 오류가 있습니다.", HttpStatus.UNAUTHORIZED);
		
		return res;
	}
}
