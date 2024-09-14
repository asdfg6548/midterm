package com.management.product.view;

import com.common.SearchCondition;
import com.management.product.controller.ProductController;
import com.management.product.model.dto.ProductDTO;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProductMenu {

    // 메인 메뉴를 출력하고 사용자로부터 입력을 받아 선택된 메뉴에 따라 해당 기능을 수행하는 메서드
    public void displayMenu() throws IOException {
        Scanner sc = new Scanner(System.in);
        ProductController productController = new ProductController();

        // 사용자에게 반복적으로 메뉴를 출력하여 선택을 받음
        do {
            System.out.println("======== Product Info 관리 ========");
            System.out.println("생산 및 판매 제품 정보 관리 화면입니다.");
            System.out.println("===================================");
            System.out.println("1. 전체 제품 정보 조회");
            System.out.println("2. 조건부 제품 정보 조회");
            System.out.println("3. 신규 제품 정보 등록");
            System.out.println("4. 기존 제품 정보 수정");
            System.out.println("5. 판매부진 단종 제품 삭제");
            System.out.println("9. 이전 메뉴로 이동");
            System.out.println("===================================");
            System.out.println("원하는 관리 메뉴의 번호를 입력해 주세요 : ");
            int selectMenu = sc.nextInt();

            // 선택된 메뉴에 따라 해당하는 ProductController 메서드를 호출
            switch (selectMenu) {
                case 1 : productController.selectAllProductList(); break;
                case 2 : productController.selectProductByCondition(inputSearchCondition()); break;
                case 3 : productController.registNewProduct(inputNewProductInfo()); break;
                case 4 : productController.modifyProductInfo(inputModifyProductInfo()); break;
                case 5 : productController.deleteProduct(inputProductCode()); break;
                case 9 : System.out.println("========상위 메뉴로 이동합니다.========"); return;
                default : System.out.println("잘못된 번호입니다. 확인 후 다시 입력해 주세요."); break;
            }

        } while(true);
    }

    // 조건부 제품 조회 시 검색 조건을 입력받는 메서드
    private static SearchCondition inputSearchCondition() {

        Scanner sc = new Scanner(System.in);
        String searchOption = "";
        String searchValue = "";

        System.out.println("===================================");
        System.out.println("조회하고 싶은 조건을 선택하세요. ");
        System.out.println("===================================");
        System.out.println("1. 제품명으로 조회");
        System.out.println("2. 이달의 신제품 조회");
        System.out.println("3. 생산 중단 제품 조회");
        System.out.println("9. 상위 메뉴로 돌아가기");
        System.out.println("===================================");
        System.out.println("원하는 조건의 번호를 입력해 주세요 : ");
        int selectMenu = sc.nextInt();

        // 사용자가 선택한 조건에 맞는 옵션과 값을 설정
        switch (selectMenu) {
            case 1 :
                sc.nextLine(); // 버퍼 비우기
                searchOption = "productName";
                System.out.println("조회할 제품명을 입력해 주세요 : ");
                searchValue = sc.nextLine();
                break;
            case 2 :
                searchOption = "newProduct";
                break;
            case 3 :
                searchOption = "nonProduction";
                break;
            case 9 : System.out.println("========상위 메뉴로 이동합니다.========"); return null;
            default : System.out.println("잘못된 번호입니다. 확인 후 다시 입력해 주세요."); break;
        }

        // 검색 조건 객체를 생성하고 반환
        SearchCondition searchCondition = new SearchCondition();
        searchCondition.setOption(searchOption);
        searchCondition.setValue(searchValue);

        return searchCondition;
    }

    // 신규 제품 등록 시 제품 정보를 입력받는 메서드
    private static ProductDTO inputNewProductInfo() {
        Scanner sc = new Scanner(System.in);

        System.out.println("===================================");
        System.out.println("등록할 새로운 제품 정보를 입력하세요. ");
        System.out.println("===================================");

        // 새로운 제품 정보를 담을 DTO 생성
        ProductDTO productDTO = new ProductDTO();
        getProductInfo(productDTO); // 제품 정보 입력
        System.out.println("===================================");

        return productDTO;
    }

    // 기존 제품 수정 시 제품 정보를 입력받는 메서드
    private static ProductDTO inputModifyProductInfo() {
        Scanner sc = new Scanner(System.in);

        System.out.println("===================================");
        System.out.println("수정할 제품 정보를 입력하세요. ");
        System.out.println("===================================");
        System.out.println("수정 대상 제품코드를 입력해 주세요 : ");
        String productCode = sc.nextLine();

        // 수정할 제품 정보를 담을 DTO 생성 및 제품코드 설정
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductCode(productCode);

        getProductInfo(productDTO); // 수정할 제품 정보 입력

        // 판매량 및 생산 상태 입력
        System.out.println("제품의 판매량을 입력해 주세요 : ");
        String salesQuantity  = sc.nextLine();
        System.out.println("제품의 생산여부를 입력해 주세요(Y:생산중 / H:생산보류 / N:생산중단) : ");
        String productionStatus = sc.nextLine().toUpperCase();

        productDTO.setSalesQuantity(salesQuantity);
        productDTO.setProductionStatus(productionStatus);

        System.out.println("===================================");

        return productDTO;
    }

    // 제품 정보(이름, 분류코드, 원가, 출시일 등)를 입력받는 공통 메서드
    private static ProductDTO getProductInfo(ProductDTO productDTO) {
        Scanner sc = new Scanner(System.in);

        System.out.println("제품명을 입력해 주세요 : ");
        String productName = sc.nextLine();
        System.out.println("제품의 분류코드를 입력해 주세요 : ");
        String categoryCode = sc.nextLine();
        System.out.println("제품의 원가를 입력해 주세요 : ");
        String originCost = sc.nextLine();
        System.out.println("제품의 출시일울 입력해 주세요(2000-01-01 형식) : ");
        String releaseDate = sc.nextLine();
        System.out.println("제품의 재고량을 입력해 주세요 : ");
        String stockQuantity  = sc.nextLine();
        System.out.println("제품의 할인율을 입력해 주세요 : ");
        String discountRate  = sc.nextLine();

        // 입력받은 정보들을 DTO에 설정
        productDTO.setProductName(productName);
        productDTO.setCategoryCode(categoryCode);
        productDTO.setOriginCost(originCost);
        productDTO.setReleaseDate(releaseDate);
        productDTO.setStockQuantity(stockQuantity);
        productDTO.setDiscountRate(discountRate);

        return productDTO;
    }

    // 삭제할 제품 코드를 입력받는 메서드
    private static Map<String, String> inputProductCode() {
        Scanner sc = new Scanner(System.in);

        System.out.println("===================================");
        System.out.println("삭제할 제품의 코드를 입력해 주세요 : ");
        String productCode = sc.nextLine();
        System.out.println("===================================");

        // 제품 코드를 Map에 담아 반환
        Map<String, String> parameter = new HashMap<>();
        parameter.put("productCode", productCode);

        return parameter;
    }
}
