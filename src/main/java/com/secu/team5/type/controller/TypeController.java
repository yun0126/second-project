package com.secu.team5.type.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.secu.team5.type.service.TypeService;
import com.secu.team5.type.vo.TypeVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TypeController {
	
	private final TypeService typeService;
	
	//야구 축구 헬스 카테고리 뽑기
	@GetMapping("/type-infos")
	List<TypeVO> getMainType(TypeVO type){
		log.info("type=>{}", type);
		return typeService.selectType(type);
	}
	
	//야구 축구 헬스와 관련된 카테고리 뽑아오기
	@GetMapping("/type-infos/{tiNum}")
	List<TypeVO> getSubType(@PathVariable int tiNum){
		log.info("tiNum =>{}", tiNum);
		return typeService.selectSubTypeWithType(tiNum);
	}

	//야구 축구 헬스 지정안하고 전부 카테고리 뽑아오기
	@GetMapping("/subtype-infos")
	public List<TypeVO> selectSubType(){
		return typeService.selectSubType();
	}
	
	//서브타입 뷰
	@GetMapping("/subtype-infos/{stiNum}")
	public TypeVO subTypeView(@PathVariable int stiNum){
		log.info("stiNum=>{}", stiNum);
		return typeService.subTypeView(stiNum);
	}
	
	//상품 상세페이지 타입/부타입
	@GetMapping("/subtype-infos/type")
	public TypeVO selectProductTypeAndSubType(@Param("piNum")int piNum) {
		log.info("piNum=>{}", piNum);
		return typeService.selectProductTypeAndSubType(piNum);
	}
	
	//타입 업데이트
	@PatchMapping("/subtype-infos")
	public int updateSubType(@RequestBody TypeVO type) {
		log.info("type=>{}",type);
		return typeService.subTypeUpdate(type);
	}
	//서브타입 추가
	@PostMapping("/subType-infos")
	public int insertSubType(@RequestBody TypeVO type) {
		return typeService.insertSubTypeInfo(type);
	}
	
	//서브타입 삭제
	@DeleteMapping("/subType-infos")
	public int deleteSubType(@RequestBody TypeVO type) {
		return typeService.subTypeDelete(type);
	}
	
	//모든 카테고리 조회
	//기존 url /type-infosAll
	@GetMapping("/type-infos/subType-infos")
	public List<TypeVO> Allselect(TypeVO type){
		return typeService.selectAllTypeAndSubType(type);
	}
}
