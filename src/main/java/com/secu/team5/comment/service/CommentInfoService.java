package com.secu.team5.comment.service;

import java.security.Principal;
import java.text.DecimalFormat;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.secu.team5.comment.mapper.CommentInfoMapper;
import com.secu.team5.comment.vo.CommentInfoVO;
import com.secu.team5.comment.vo.CountVO;
import com.secu.team5.user.mapper.UserInfoMapper;
import com.secu.team5.user.vo.UserInfoVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentInfoService {

	private final CommentInfoMapper cmiMapper;
	
	private final UserInfoMapper uiMapper;
	
	// 리뷰 입력
	public int insertComment(CommentInfoVO comment, Principal principal) {
		UserInfoVO user = new UserInfoVO();
		user = uiMapper.selectUserInfoById(principal.getName());
		comment.setUiNum(user.getUiNum());
		
		
		log.info("comment=>{}",comment);
		return cmiMapper.insertCommentInfo(comment);
	}
	
	// 해당 상품 리뷰 조회
	public List<CommentInfoVO> getCommentInfosbypiNum(int piNum) {
		return cmiMapper.selectCommentInfosbypiNum(piNum);
	}
	
	// 내 리뷰내역 조회
	public PageInfo<CommentInfoVO> selectMyCommentsWithHelper(CommentInfoVO comment, Principal principal) {
		UserInfoVO user = new UserInfoVO();
		user = uiMapper.selectUserInfoById(principal.getName());
		comment.setUiNum(user.getUiNum());
		PageHelper.startPage(comment.getPage(), comment.getPageSize());
		PageInfo<CommentInfoVO> pageInfo = new PageInfo<>(cmiMapper.selectMyCommentsWithHelper(comment), 10);
		return pageInfo;
	}
	
	// 상품 리뷰 페이징 조회
	public PageInfo<CommentInfoVO> selectCommentInfosbypiNumWithHelper(CommentInfoVO comment) {
		log.info("comment=>{}",comment);
		PageHelper.startPage(comment.getPage(), comment.getPageSize());
		PageInfo<CommentInfoVO> pageInfo = new PageInfo<>(cmiMapper.selectCommentInfosbypiNumWithHelper(comment), 10);
		return pageInfo;
	}
	
	// 해당 리뷰 조회
	public CommentInfoVO selectCommentInfoswithCiNum(int ciNum) {
		return cmiMapper.selectCommentInfoswithCiNum(ciNum);
	}
	
	// 내 리뷰 수정
	public int updateMyComment(CommentInfoVO comment) {
		log.info("comment=>{}",comment);
		return cmiMapper.updateMyComment(comment);
	}
	
	// 내 리뷰 삭제
	public int deleteMyComment(CommentInfoVO comment) {
		return cmiMapper.deleteCommentInfo(comment);
	}
	
	// 리뷰 중복 작성 방지
	public CountVO checkInsertComment(int odiNum, int piNum) {
		CommentInfoVO checks = new CommentInfoVO();
		checks.setOdiNum(odiNum);
		checks.setPiNum(piNum);
		log.info("checks=>{}",checks);
		return cmiMapper.checkInsertComment(checks);
	}
	
	// 해당 상품 평점 평균
	public float avgComment(int piNum) {
		List<CommentInfoVO> comments = cmiMapper.selectCommentInfosbypiNum(piNum);
		int sum = 0;
		float avg = 0;
		if(comments != null) {
			for(CommentInfoVO comment : comments) {
				sum += comment.getCiScore();
			}
			if(sum != 0) {
				avg = (float)sum/comments.size();
				DecimalFormat decimalFormat = new DecimalFormat("#.#");
	            avg = Float.parseFloat(decimalFormat.format(avg));
			}
		} 
		return avg;
	}
	
	public int countComments(int piNum) {
		List<CommentInfoVO> comments = cmiMapper.selectCommentInfosbypiNum(piNum);
		int counts = 0;
		if(comments != null) {
			for(CommentInfoVO comment : comments) {
				counts++;
			}
		}
		return counts;
	}
	
	public static void main(String[] args) {
		double kk = 3.1556223;
		System.out.println(Math.round(kk*100)/100.0);
	}
	
}
