package com.management.product.view;

import com.common.SearchCondition;
import com.management.product.model.dto.ProductDTO;

import java.util.List;

public class ProductPrint {

    // 전체 제품 목록을 출력하는 메서드
    public void printAllProductList(List<ProductDTO> allProductList) {
        System.out.println("===== 전체 제품 목록 =====");
        // 제품 목록을 순회하며 각 제품 정보를 출력
        for(ProductDTO product : allProductList) {
            System.out.println(product);
        }
        System.out.println("========================");
    }

    // 검색 조건에 따른 제품 목록을 출력하는 메서드
    public void printProductList(List<ProductDTO> productList, SearchCondition searchCondition) {
        System.out.println("===== 검색 조건에 따른 제품 목록 =====");
        // 검색 조건과 해당 조건에 맞는 제품 목록을 출력
        System.out.println("검색 조건: " + searchCondition);
        for(ProductDTO product : productList) {
            System.out.println(product);
        }
        System.out.println("===================================");
    }

    // 성공 메시지를 출력하는 메서드
    public void printSuccessMessage(String successCode) {
        System.out.println("===== 성공 메시지 =====");
        // 성공 메시지를 출력
        System.out.println(successCode);
        System.out.println("======================");
    }

    // 에러 메시지를 출력하는 메서드
    public void printErrorMessage(String errorCode) {
        System.out.println("===== 에러 메시지 =====");
        // 에러 메시지를 출력
        System.out.println(errorCode);
        System.out.println("======================");
    }
}
