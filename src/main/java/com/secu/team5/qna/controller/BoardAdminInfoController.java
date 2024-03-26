package com.secu.team5.qna.controller;

import java.io.IOException;
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
import com.secu.team5.common.util.StringUtils;
import com.secu.team5.qna.service.BoardAdminInfoService;
import com.secu.team5.qna.vo.BoardAdminInfoVO;
import com.secu.team5.qna.vo.ckVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
public class BoardAdminInfoController {

	private final BoardAdminInfoService baiService;

	private final AmazonS3Client amazonS3Client;

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	@Value("${bucket.file-Path}")
	private String Path;

	// ckEditor 이미지 업로드
	@Value("${upload.file-path}")
	private String filePath;

	@PostMapping("/uploads-ck-img")
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
				// ck editor가 응답받길원하는것
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

	// 게시판 페이징
	@GetMapping("/board-admin-infos/helper")
	public PageInfo<BoardAdminInfoVO> getBoardInfosWithHelper(BoardAdminInfoVO board, Principal principal) {
		return baiService.selectBoardInfosWithHelper(board, principal);
	}

	// 답변 없을때 업데이트 버튼 숨기기 위해 답변 카운트 세는 부분
	@GetMapping("/board-admin-infos/reply-count/{bqiNum}")
	public int replyCount(@PathVariable int bqiNum) {
		log.info("bqiNum=>{}", bqiNum);
		return baiService.replyCount(bqiNum);
	}

	// 게시물 상세보기
	@GetMapping("/board-admin-infos/{bqiNum}")
	public BoardAdminInfoVO getBoards(@PathVariable int bqiNum) {
		return baiService.getBoardInfo(bqiNum);
	}

	// 답변 작성 /insert-board-admin
	@PostMapping("/board-admin-infos")
	public int writeBoardAdmin(@RequestBody BoardAdminInfoVO board, Principal principal) {
		log.info("board=>{}", board);
		return baiService.insertBoardInfo(board, principal);
	}

	// 답변 수정 /update-board-admin
	@PatchMapping("/board-admin-infos")
	public int updateBoard(@RequestBody BoardAdminInfoVO board, Principal principal) {
		log.info("board=>{}", board);
		return baiService.updateBoardInfo(board, principal);
	}

	// 답변 삭제
	@DeleteMapping("/board-admin-infos/{rpiNum}")
	public int deleteBoard(@PathVariable int rpiNum) {
		return baiService.deleteBoardInfo(rpiNum);
	}

	// 미답변 조회 페이징
	@GetMapping("/board-admin-infos/reply")
	public PageInfo<BoardAdminInfoVO> nonReplyList(BoardAdminInfoVO board, Principal principal) {
		return baiService.selectBoardInfosWithHelperList(board, principal);
	}
}
