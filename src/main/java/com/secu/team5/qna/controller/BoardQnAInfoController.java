package com.secu.team5.qna.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
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
import com.secu.team5.qna.service.BoardQnAInfoService;
import com.secu.team5.qna.vo.BoardQnAInfoVO;
import com.secu.team5.qna.vo.ckVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
public class BoardQnAInfoController {

	private final BoardQnAInfoService bqiService;

	private final AmazonS3Client amazonS3Client;

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	@Value("${bucket.file-Path}")
	private String Path;

	// 게시판 페이징
	@GetMapping("/board-qna-infos/helper")
	public PageInfo<BoardQnAInfoVO> getBoardInfosWithHelper(BoardQnAInfoVO board, Principal principal) {
		return bqiService.selectBoardInfosWithHelper(board, principal);
	}

	// 마이페이지에서 문의사항 리스트 불러오는 부분
	@GetMapping("/board-qna-infos")
	public List<BoardQnAInfoVO> selectBoardInfos(BoardQnAInfoVO board, Principal principal) {
		return bqiService.selectBoardInfos(board, principal);
	}

	// 게시물 상세보기
	@GetMapping("/board-qna-infos/{bqiNum}")
	public BoardQnAInfoVO getBoards(@PathVariable int bqiNum) {
		return bqiService.getBoardInfo(bqiNum);
	}

	// 게시물 작성 /insert-board-qna
	@PostMapping("/board-qna-infos")
	public int writeBoardQnA(@RequestBody BoardQnAInfoVO board, Principal principal) {
		log.info("board=>{}", board);
		return bqiService.insertBoardInfo(board, principal);
	}

	// ckEditor 이미지 업로드
	@Value("${upload.file-path}")
	private String filePath;

	@PostMapping("/upload-ck-img")
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

	// 게시물 수정 /update-board-qna
	@PatchMapping("/board-qna-infos")
	public int updateBoard(@RequestBody BoardQnAInfoVO board, Principal principal) {
		log.info("board=>{}", board);
		return bqiService.updateBoardInfo(board, principal);
	}

	// 게시물 삭제 /delete-board-qna/{bqiNum}
	@DeleteMapping("/board-qna-infos/{bqiNum}")
	public int deleteBoard(@PathVariable int bqiNum) {
		return bqiService.deleteBoardInfo(bqiNum);
	}

}
