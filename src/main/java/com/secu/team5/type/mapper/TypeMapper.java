package com.secu.team5.type.mapper;

import java.util.List;

import com.secu.team5.type.vo.TypeVO;

public interface TypeMapper {
	//야구 축구 헬스 카테고리 뽑기
	List<TypeVO> selectType(TypeVO type);
	//야구 축구 헬스와 관련된 카테고리 뽑아오기	
	List<TypeVO> selectSubTypeWithType(int tiNum);
	//야구 축구 헬스 지정안하고 전부 카테고리 뽑아오기
	List<TypeVO> selectSubType();
	// product-view에 표기할 타입, 서브타입 뽑기
	TypeVO selectProductTypeAndSubType(int piNum);
	
	
	//SUB TYPE SELECT
	TypeVO selectSubTypeinfo(int stiNum);
	//SUB TYPE INSERT
	int insertSubTypeInfo(TypeVO type);
	//SUB TYPE UPDATE
	int updateSubTypeUpdate(TypeVO type);
	//SUB TYPE DELETE
	int deleteSubType(TypeVO type);

	
	
	//ALL TYPE SELECT
	List<TypeVO> selectAllTypeAndSubType(TypeVO type);
}
