package com.secu.team5.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.secu.team5.board.mapper.BoardFileInfoMapper;
import com.secu.team5.board.vo.BoardFileInfoVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardFileInfoService {

	private final BoardFileInfoMapper bfiMapper;
	
	//조회 기준점은 파일번호
	public List<BoardFileInfoVO> selectBoardsFileInfos(int bfiNum) {
		return bfiMapper.selectBoardFileInfos(bfiNum);
	}
	//조회 기준점은 게시판 번호
	public BoardFileInfoVO selectBoardsFileInfosWithBiNum(int biNum) {
		return bfiMapper.selectBoardFileWithBiNum(biNum);
	}
	//추가 
	public int insertBoardFileInfo(BoardFileInfoVO boardFiles) {
		return bfiMapper.insertBoardFileInfo(boardFiles);
	}

	// 간단하게 텍스트로 저장함
	public int insertBoardsFileInfos(int biNum, BoardFileInfoVO boardFile) {
		int result = 0;
		boardFile.setBiNum(biNum);
		log.info("boardFile=>{}", boardFile);
		result += bfiMapper.insertBoardFileInfo(boardFile);
		return result;
	}
	
	//수정
	public int updateBoardsFileInfos(int biNum, List<BoardFileInfoVO> boardFiles) {
		int result = 0;
		BoardFileInfoVO boardFile = boardFiles.get(0);
		boardFile.setBiNum(biNum);
		result += bfiMapper.updateBoardFileInfo(boardFile);
		return result;

	}
	//삭제
	public int deleteBoardFileInfo(int bfiNum) {
		return bfiMapper.deleteBoardFileInfo(bfiNum);
	}
}
