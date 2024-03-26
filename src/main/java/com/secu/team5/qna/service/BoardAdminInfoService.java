package com.secu.team5.qna.service;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.secu.team5.qna.mapper.BoardAdminInfoMapper;
import com.secu.team5.qna.vo.BoardAdminInfoVO;
import com.secu.team5.user.mapper.UserInfoMapper;
import com.secu.team5.user.vo.UserInfoVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardAdminInfoService {

	private final BoardAdminInfoMapper baiMapper;
	private final UserInfoMapper userInfoMapper;

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	@Value("${bucket.file-Path}")
	private String Path;

	// 페이징 헬퍼를 사용한 게시판 페이징
	public PageInfo<BoardAdminInfoVO> selectBoardInfosWithHelper(BoardAdminInfoVO board, Principal principal) {
		// 현재 로그인한 사용자의 정보를 가져옴
		UserInfoVO user = userInfoMapper.selectUserInfoById(principal.getName());
		// 현재 로그인한 사용자의 uiNum을 세팅
		board.setUiNum(user.getUiNum());

		PageHelper.startPage(board.getPage(), board.getPageSize());

		PageInfo<BoardAdminInfoVO> pageInfo = new PageInfo<>(baiMapper.selectBoardInfosWithHelper(board), 10);
		return pageInfo;
	}

	// 상세 게시물 조회
	public BoardAdminInfoVO getBoardInfo(int bqiNum) {
		BoardAdminInfoVO board = baiMapper.selectBoardInfo(bqiNum);
		return board;
	}

	// 답변 세는 부분
	public int replyCount(int bqiNum) {
		return baiMapper.replyCountByBqiNum(bqiNum);
	}

	// 게시물 등록
	public int insertBoardInfo(BoardAdminInfoVO board, Principal principal) {
		UserInfoVO user = userInfoMapper.selectUserInfoById(principal.getName());
		int result = 0;
		log.info("user=>{}", user);

		// 해당 게시물에 이미 답변이 있는지 체크
		int replyCount = baiMapper.replyCountByBqiNum(board.getBqiNum());

		if (replyCount > 0) {
			// 이미 답변이 있는 경우, 중복 등록을 나타내는 -1을 사용
			result = -1;
			log.info("result=>{}", result);
		} else {
			// session에서 가져온 uiNum을 보드에 대입함
			board.setUiNum(user.getUiNum());
			// 내용 저장
			result = baiMapper.insertBoardInfo(board);
			log.info("board=>{}", board);

			// 텍스트 파일로 저장하는 로직을 제거하고 간소화
		}

		return result;
	}

	@Transactional
	// 답변 수정
	public int updateBoardInfo(BoardAdminInfoVO board, Principal principal) {
		log.info("board=>{}", board);
		return baiMapper.updateBoardInfo(board);
	}

	// 답변 삭제
	public int deleteBoardInfo(int rpiNum) {
		log.info("rpiNum=>{}", rpiNum);
		return baiMapper.deleteBoardInfo(rpiNum);
	}

	// 페이징 헬퍼를 사용한 미답변 게시판 페이징
	public PageInfo<BoardAdminInfoVO> selectBoardInfosWithHelperList(BoardAdminInfoVO board, Principal principal) {
		// 현재 로그인한 사용자의 정보를 가져옴
		UserInfoVO user = userInfoMapper.selectUserInfoById(principal.getName());
		// 현재 로그인한 사용자의 uiNum을 세팅
		board.setUiNum(user.getUiNum());

		PageHelper.startPage(board.getPage(), board.getPageSize());

		PageInfo<BoardAdminInfoVO> pageInfo = new PageInfo<>(baiMapper.selectBoardInfosWithHelperList(board), 10);
		return pageInfo;
	}
}
