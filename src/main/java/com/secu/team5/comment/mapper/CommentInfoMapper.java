package com.secu.team5.comment.mapper;

import java.util.List;

import com.github.pagehelper.Page;
import com.secu.team5.comment.vo.CommentInfoVO;
import com.secu.team5.comment.vo.CountVO;

public interface CommentInfoMapper {
	List<CommentInfoVO> selectCommentInfosbypiNum(int piNum);

	Page<CommentInfoVO> selectMyCommentsWithHelper(CommentInfoVO comment);
	
	Page<CommentInfoVO> selectCommentInfosbypiNumWithHelper(CommentInfoVO comment);

	CommentInfoVO selectCommentInfoswithCiNum(int ciNum);

	int insertCommentInfo(CommentInfoVO comment);

	int updateMyComment(CommentInfoVO comment);

	int deleteCommentInfo(CommentInfoVO comment);

	CountVO checkInsertComment(CommentInfoVO comment);
	
	
}
