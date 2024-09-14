package com.management.product.model.service;

import com.common.SearchCondition;
import com.common.Template;
import com.management.product.model.dao.ProductDAO;
import com.management.product.model.dto.ProductDTO;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ProductService {

    private ProductDAO productDAO;

    // 모든 제품 목록을 조회하는 서비스 메서드
    public List<ProductDTO> selectAllProductList() throws IOException {
        // SqlSession을 통해 MyBatis와의 연결을 열고, ProductDAO 매퍼를 가져옴
        SqlSession sqlSession = Template.getSqlSession();
        productDAO = sqlSession.getMapper(ProductDAO.class);

        // ProductDAO를 사용해 모든 제품 목록을 조회
        List<ProductDTO> productList = productDAO.selectAllProductList();

        // SqlSession을 닫음
        sqlSession.close();

        return productList;
    }

    // 조건에 맞는 제품 목록을 조회하는 서비스 메서드
    public List<ProductDTO> selectProductByCondition(SearchCondition searchCondition) throws IOException {
        // SqlSession을 열고 ProductDAO 매퍼를 가져옴
        SqlSession sqlSession = Template.getSqlSession();
        productDAO = sqlSession.getMapper(ProductDAO.class);

        // 검색 조건에 맞는 제품 목록을 조회
        List<ProductDTO> productList = productDAO.selectProductByCondition(searchCondition);

        // SqlSession을 닫음
        sqlSession.close();

        return productList;
    }

    // 새로운 제품을 등록하는 서비스 메서드
    public boolean registNewProduct(ProductDTO product) throws IOException {
        // SqlSession을 열고 ProductDAO 매퍼를 가져옴
        SqlSession sqlSession = Template.getSqlSession();
        productDAO = sqlSession.getMapper(ProductDAO.class);

        // ProductDAO를 사용해 제품을 등록하고, 결과 값을 받음
        int result = productDAO.insertProduct(product);

        // 등록 성공 시 커밋, 실패 시 롤백
        if(result > 0) {
            sqlSession.commit();
        } else {
            sqlSession.rollback();
        }

        // SqlSession을 닫음
        sqlSession.close();

        return result > 0;
    }

    // 제품 정보를 수정하는 서비스 메서드
    public boolean modifyProductInfo(ProductDTO product) throws IOException {
        // SqlSession을 열고 ProductDAO 매퍼를 가져옴
        SqlSession sqlSession = Template.getSqlSession();
        productDAO = sqlSession.getMapper(ProductDAO.class);

        // ProductDAO를 사용해 제품 정보를 수정하고, 결과 값을 받음
        int result = productDAO.updateProduct(product);

        // 수정 성공 시 커밋, 실패 시 롤백
        if(result > 0) {
            sqlSession.commit();
        } else {
            sqlSession.rollback();
        }

        // SqlSession을 닫음
        sqlSession.close();

        return result > 0;
    }

    // 제품을 삭제하는 서비스 메서드
    public boolean deleteProduct(Map<String, String> parameter) throws IOException {
        // SqlSession을 열고 ProductDAO 매퍼를 가져옴
        SqlSession sqlSession = Template.getSqlSession();
        productDAO = sqlSession.getMapper(ProductDAO.class);

        // ProductDAO를 사용해 제품을 삭제하고, 결과 값을 받음
        int result = productDAO.deleteProduct(parameter);

        // 삭제 성공 시 커밋, 실패 시 롤백
        if(result > 0) {
            sqlSession.commit();
        } else {
            sqlSession.rollback();
        }

        // SqlSession을 닫음
        sqlSession.close();

        return result > 0;
    }
}
