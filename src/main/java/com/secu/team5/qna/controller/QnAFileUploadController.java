package com.secu.team5.qna.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

//aws로 파일 올리는 예제 + ckeditor까지 사용해봤음 사용요금체크해야함.. 이거 돈나가면 큰일남 -ㅅ- / ㅋㅋㅋ 복사를 어케햇길래 이런주석까지 복사함ㅋㅋ
@RestController
@RequiredArgsConstructor
public class QnAFileUploadController {
	
	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	@Value("${bucket.file-Path}")
	private String Path;

}
