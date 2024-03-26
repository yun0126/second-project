package com.secu.team5.type.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.secu.team5.type.mapper.TypeMapper;
import com.secu.team5.type.vo.TypeVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TypeService {
	
	private final TypeMapper typeMapper;
	
	//메인타입
	public List<TypeVO> selectType(TypeVO type){
		return typeMapper.selectType(type);
	}
	
	//서브타입 메인타입에 관계있이
	public List<TypeVO> selectSubTypeWithType(int tiNum){
		return typeMapper.selectSubTypeWithType(tiNum);
	}
	
	//서브타입 메인타입에 관계없이
	public List<TypeVO> selectSubType(){
		return typeMapper.selectSubType();
	}
	
	public TypeVO selectProductTypeAndSubType(int piNum) {
		return typeMapper.selectProductTypeAndSubType(piNum);
	}
	
	//서브타입 값만 가져옴
	public TypeVO subTypeView(int stiNum){
		return typeMapper.selectSubTypeinfo(stiNum);
	}
	
	//서브타입 추가
	public int insertSubTypeInfo(TypeVO type) {
		return typeMapper.insertSubTypeInfo(type);
	}
	
	//서브타입 업데이트
	public int subTypeUpdate(TypeVO type) {
		return typeMapper.updateSubTypeUpdate(type);
	}
	
	//서브타입 삭제
	public int subTypeDelete(TypeVO type) {
		return typeMapper.deleteSubType(type);
	}
	
	//All Type select
	public List<TypeVO> selectAllTypeAndSubType(TypeVO type){
		return typeMapper.selectAllTypeAndSubType(type);
	}
}
