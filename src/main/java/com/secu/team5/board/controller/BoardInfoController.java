package com.secu.team5.board.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.github.pagehelper.PageInfo;
import com.secu.team5.board.service.BoardInfoService;
import com.secu.team5.board.vo.BoardInfoVO;
import com.secu.team5.board.vo.ckVO;
import com.secu.team5.common.util.StringUtils;
import com.secu.team5.common.vo.ResponsePageVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
public class BoardInfoController {

	
	private final BoardInfoService biService;
	
	private final AmazonS3Client amazonS3Client;

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;
	
	@Value("${bucket.file-Path}")
	private String Path;
	
	//ckEditor 이미지 업로드
	@Value("${upload.file-path}")
	private String filePath;
	
	// 게시판 리스트
	@GetMapping("/board-infos")
	public ResponsePageVO<BoardInfoVO> getBoardInfos(BoardInfoVO board) {
		return biService.selectBoardInfos(board);
	}
	
	// 게시판 페이징
	@GetMapping("/board-infos/helper")
	public PageInfo<BoardInfoVO> getBoardInfosWithHelper(BoardInfoVO board){
		return biService.selectBoardInfosWithHelper(board);
	}
	
	// 게시물 상세보기
	// 기존 url /board-info/{biNum}
	@GetMapping("/board-infos/{biNum}")
	public BoardInfoVO getBoards(@PathVariable int biNum) {
		return biService.getBoardInfo(biNum);
	}
	
	// 게시물 작성
	// 기존 url /insert-board
	@PostMapping("/board-infos") 
	public int writeBoard(@RequestBody BoardInfoVO board, Principal principal) throws IllegalStateException, IOException {
		log.info("board=>{}", board);
		return biService.insertBoardInfo(board, principal);
	}
	

	
	// 게시물 수정
	// 기존 url /update-board
	@PatchMapping("/board-infos")
	public int updateBoard(@RequestBody BoardInfoVO board , Principal principal) throws UnsupportedEncodingException {
		log.info("board=>{}", board);
		
		return biService.updateBoardInfo(board, principal); 
	}
	

	// 게시물 삭제
	// 기존 url /delete-board
	@DeleteMapping("/board-infos/{biNum}")
	public int deleteBoard(@PathVariable int biNum) {
		return biService.deleteBoardInfo(biNum);
	}
	
	
	// 조회수 증가
	@PatchMapping("/update-view/{biNum}")
	public int updateView(@PathVariable int biNum) {
	    BoardInfoVO board = new BoardInfoVO();
	    board.setBiNum(biNum);
	    return biService.updateBoardView(board);
	}
	
	
	//이미지 업로드
	@PostMapping("/upload-img")
	public ckVO ckUpload(MultipartFile upload, ckVO ck) throws IllegalStateException, IOException {
		log.info("upload=>{}", upload);
		if (upload != null && !upload.getOriginalFilename().isEmpty()) {
			try {
				String fileName = upload.getOriginalFilename();
				String uuid = UUID.randomUUID().toString();
				String extName = StringUtils.getExt(fileName);
				fileName = uuid + extName;
				// AWS S3로 파일 보내는 주소
				String filePath = "https://" + bucket + "/" + fileName;
				// 보내기위한 데이타 셋팅
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentType(upload.getContentType());
				metadata.setContentLength(upload.getSize());
				amazonS3Client.putObject(bucket, fileName, upload.getInputStream(), metadata);
				// 데이터 보내기
				ResponseEntity.ok(filePath);
				//ck editor가 응답받길원하는것 
				ck.setUploaded(1);
				ck.setFileName(fileName);
				ck.setUrl(Path + fileName);
				
			} catch (Exception e) {
				e.printStackTrace();
				ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

			}

		}
		
		return ck;
		
	}
	
}
