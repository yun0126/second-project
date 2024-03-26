package com.secu.team5.product.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.secu.team5.product.service.ProductInfoService;
import com.secu.team5.product.vo.ProductInfoVO;
import com.secu.team5.product.vo.ProductVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ProductInfoController {
	
	@Autowired
	private ProductInfoService productInfoService;
	

	//상품 추가
	@PostMapping("/product-infos")
	public int addProduct(ProductInfoVO product, Principal principal) throws IllegalStateException, IOException {
		log.info("product=>{}", product);
		return productInfoService.addProductInfo(product, principal);
	}
	
	//상품 리스트
	@GetMapping("/product-infos")
	public List<ProductVO> listProduct(ProductVO product){
		//매개변수에 productinfovo 를 넣은것은 검색용
		log.info("ProductVO=>{}",product);
		log.info("ProductCount=>{}",productInfoService.selectProductsCount(product));
		return productInfoService.selectProducts(product);
	}
	
	//상품 페이징을 위한 리스트
	@GetMapping("/product-infos/helper")
	public PageInfo<ProductVO> getProductList(ProductVO product){
		return productInfoService.selectProductsWithHelper(product);
	}
	
	//상품 보기
	@GetMapping("/product-infos/{piNum}")
	public ProductInfoVO viewProduct(@PathVariable int piNum) {
		return productInfoService.selectProductInfo(piNum);
	}
	
	//상품 변경
	@PatchMapping("/product-infos")
	public int updateProductInfos(ProductInfoVO product) {
		log.info("product=>{}", product);
		return productInfoService.updateGoodsInfo(product);
	}
	
	//상품 삭제 - 데이터베이스를 캐스캐이딩으로 만들어놨기때문에 하나만삭제해도 관련된거 전부 삭제된다
	@DeleteMapping("/product-infos/{piNum}")
	public int deleteProductInfos(@PathVariable int piNum) {
		log.info("piNum=>{}",piNum);
		return productInfoService.deleteProduct(piNum);
	}
	
	
	
}
