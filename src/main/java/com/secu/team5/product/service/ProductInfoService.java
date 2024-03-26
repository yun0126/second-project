package com.secu.team5.product.service;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.secu.team5.product.mapper.ProductInfoMapper;
import com.secu.team5.product.vo.ProductInfoVO;
import com.secu.team5.product.vo.ProductVO;
import com.secu.team5.user.mapper.UserInfoMapper;
import com.secu.team5.user.vo.UserInfoVO;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//이 서비스에서 다른 서비스에서 처리하는걸 전부 받아서 처리한다
@Service
@Slf4j
@AllArgsConstructor
public class ProductInfoService {
	private final ProductFileInfoService productFileInfoService;
	private final ProductInfoMapper productInfoMapper;
	private final ProductService productService;
	private final UserInfoMapper userInfoMapper;
	
	//상품 추가
	public int addProductInfo(ProductInfoVO product, Principal principal) {
		UserInfoVO user = userInfoMapper.selectUserInfoById(principal.getName());
		product.setUiNum(user.getUiNum());
		int result = productInfoMapper.insertProductInfo(product);
		log.info("product=>{}", product);
		result += productFileInfoService.insertProductFileInfo(product.getPiNum(), product.getProductFiles());
		
		return result;
	}
	
	//상품 수정 status에 따라 상품이 생성되냐 삭제되냐 결정이된다 
	public int updateGoodsInfo(ProductInfoVO product) {
		log.info("product=>{}", product);
		
		int result = productFileInfoService.updateProductFileInfo(product.getPiNum(), product.getProductFiles());
		result += productInfoMapper.updateProductInfo(product);
		return result;
	}
	
	//상품 삭제
	public int deleteProduct(int piNum) {
		int result = productFileInfoService.deletePfi(piNum);
		result += productInfoMapper.deleteProductInfo(piNum);
		return result;
	}
	
	//TEST
	public List<ProductInfoVO> selectProductInfos(ProductInfoVO product){
		return productInfoMapper.selectProductInfos(product);
	}
	
	
	//view or update
	public ProductInfoVO selectProductInfo(int piNum) {
		//하나의 파일에대한 사진과 정보를 모두 가져다 줌
		ProductInfoVO product = new ProductInfoVO();
		product = productInfoMapper.selectProductInfo(piNum);
		product.setProductFiles(productFileInfoService.selectProductFileInfo(piNum));
		
		return product;
	}
	
	
	//리스트 뷰를 위한 상품 목록 메소드
	public List<ProductVO> selectProducts(ProductVO product){
		return productService.selectProducts(product);
	}
	
	//페이징을 위한 상품 목록 메소드
	public int selectProductsCount(ProductVO product) {
		return productService.selectProductsCount(product);
	}
	
	//페이지 헬퍼를 사용한 상품목록 메소드
	public PageInfo<ProductVO> selectProductsWithHelper(ProductVO product){
		return productService.selectProductsWithHelper(product);
	}
	
}
