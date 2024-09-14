package com.management.product.controller;

import com.common.SearchCondition;
import com.management.product.model.dto.ProductDTO;
import com.management.product.model.service.ProductService;
import com.management.product.view.ProductPrint;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ProductController {

    // ProductService와 ProductPrint 객체 선언
    private final ProductService productService;
    private final ProductPrint productPrint;

    // 생성자에서 ProductService와 ProductPrint 객체를 초기화
    public ProductController() {
        this.productService = new ProductService();
        this.productPrint = new ProductPrint();
    }

    // 모든 제품 목록을 조회하는 메서드
    public void selectAllProductList() {
        try {
            // ProductService를 통해 모든 제품 목록을 조회
            List<ProductDTO> productList = productService.selectAllProductList();

            // 조회된 목록이 비어있지 않으면 출력, 없으면 에러 메시지 출력
            if(productList != null && !productList.isEmpty()) {
                productPrint.printAllProductList(productList);
            } else {
                productPrint.printErrorMessage("조회된 제품 목록이 없습니다.");
            }
        } catch (IOException e) {
            // 조회 중 발생한 예외를 처리
            productPrint.printErrorMessage("제품 목록 조회 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    // 조건에 따라 제품을 조회하는 메서드
    public void selectProductByCondition(SearchCondition searchCondition) {
        try {
            // 검색 조건을 기반으로 제품 목록을 조회
            List<ProductDTO> productList = productService.selectProductByCondition(searchCondition);

            // 조건에 맞는 제품이 있으면 출력, 없으면 에러 메시지 출력
            if(productList != null && !productList.isEmpty()) {
                productPrint.printProductList(productList, searchCondition);
            } else {
                productPrint.printErrorMessage("조건에 맞는 제품이 없습니다.");
            }
        } catch (IOException e) {
            // 조회 중 발생한 예외를 처리
            productPrint.printErrorMessage("조건부 제품 조회 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    // 새로운 제품을 등록하는 메서드
    public void registNewProduct(ProductDTO product) {
        try {
            // 제품의 출시일에서 '-' 문자를 제거하고, 생산 상태와 판매 수량을 기본 값으로 설정
            String releaseDate = product.getReleaseDate().replaceAll("-", "");
            product.setReleaseDate(releaseDate);
            product.setProductionStatus("Y");
            product.setSalesQuantity("0");

            // ProductService를 통해 제품 등록을 시도
            boolean result = productService.registNewProduct(product);

            // 등록 성공 여부에 따라 성공 또는 에러 메시지 출력
            if(result) {
                productPrint.printSuccessMessage("제품 등록 성공");
            } else {
                productPrint.printErrorMessage("제품 등록 실패");
            }
        } catch (IOException e) {
            // 등록 중 발생한 예외를 처리
            productPrint.printErrorMessage("제품 등록 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    // 제품 정보를 수정하는 메서드
    public void modifyProductInfo(ProductDTO product) {
        try {
            // 제품의 출시일에서 '-' 문자를 제거
            String releaseDate = product.getReleaseDate().replaceAll("-", "");
            product.setReleaseDate(releaseDate);

            // ProductService를 통해 제품 정보 수정 시도
            boolean result = productService.modifyProductInfo(product);

            // 수정 성공 여부에 따라 성공 또는 에러 메시지 출력
            if(result) {
                productPrint.printSuccessMessage("제품 정보 수정 성공");
            } else {
                productPrint.printErrorMessage("제품 정보 수정 실패");
            }
        } catch (IOException e) {
            // 수정 중 발생한 예외를 처리
            productPrint.printErrorMessage("제품 정보 수정 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    // 제품 정보를 삭제하는 메서드
    public void deleteProduct(Map<String, String> parameter) {
        try {
            // ProductService를 통해 제품 삭제 시도
            boolean result = productService.deleteProduct(parameter);

            // 삭제 성공 여부에 따라 성공 또는 에러 메시지 출력
            if(result) {
                productPrint.printSuccessMessage("제품 정보 삭제 성공");
            } else {
                productPrint.printErrorMessage("제품 정보 삭제 실패");
            }
        } catch (IOException e) {
            // 삭제 중 발생한 예외를 처리
            productPrint.printErrorMessage("제품 삭제 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}
