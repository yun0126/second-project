package com.secu.team5.comment.controller;

import java.security.Principal;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.secu.team5.comment.service.CommentInfoService;
import com.secu.team5.comment.vo.CommentInfoVO;
import com.secu.team5.comment.vo.CountVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CommentInfoController {

	public final CommentInfoService cmiService;
	
	// 리뷰 입력
	@PostMapping("/comment-infos")
	public int insertComment(@RequestBody CommentInfoVO comment, Principal principal) {
		return cmiService.insertComment(comment, principal);
	}
	
	// 해당 상품 리뷰 조회
	@GetMapping("/comment-infos/{piNum}")
	public List<CommentInfoVO> getCommentbyPiNum(@PathVariable int piNum) {
		return cmiService.getCommentInfosbypiNum(piNum);
	}
	
	// 내 리뷰내역 조회
	@GetMapping("/comment-infos/helper")
	public PageInfo<CommentInfoVO> getCommentInfoWithHelper(CommentInfoVO comment,Principal principal) {
		return cmiService.selectMyCommentsWithHelper(comment, principal);
	}
	
	@GetMapping("/comment-infos/{piNum}/helper")
	public PageInfo<CommentInfoVO> getCommentInfoWithHelper(CommentInfoVO comment) {
		return cmiService.selectCommentInfosbypiNumWithHelper(comment);
	}
	
	// 해당 리뷰 조회
	@GetMapping("/comment-info/{ciNum}")
	public CommentInfoVO getCommentInfoswithCiNum(@PathVariable int ciNum) {
		return cmiService.selectCommentInfoswithCiNum(ciNum);
	}
	
	// 내 리뷰 수정
	@PatchMapping("/comment-infos")
	public int updateMyComment(@RequestBody CommentInfoVO comment) {
		return cmiService.updateMyComment(comment);
	}
	
	// 내 리뷰 삭제
	@DeleteMapping("/comment-info")
	public int deleteMyComment(@RequestBody CommentInfoVO comment) {
		return cmiService.deleteMyComment(comment);
	}
	
	// 리뷰 중복 작성 방지
	@GetMapping("/comment-info/check")
	public CountVO checkInsertComment(@Param("odiNum")int odiNum, @Param("piNum")int piNum) {
		return cmiService.checkInsertComment(odiNum, piNum);
	}
	
	// 해당 상품 평점 평균 조회
	@GetMapping("/comment-info/average")
	public float getCommentAverage(@Param("piNum")int piNum) {
		return cmiService.avgComment(piNum);
	}
	
	@GetMapping("/comment-infos/count")
	public int getCommentsCount(@Param("piNum")int piNum) {
		return cmiService.countComments(piNum);
	}
	
}
