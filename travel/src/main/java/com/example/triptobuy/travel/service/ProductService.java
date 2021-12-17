package com.example.triptobuy.travel.service;

import com.example.triptobuy.travel.domain.Category;
import com.example.triptobuy.travel.domain.City;
import com.example.triptobuy.travel.domain.Product;
import com.example.triptobuy.travel.domain.SubCategory;
import com.example.triptobuy.travel.dto.ProductDto;
import com.example.triptobuy.travel.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    // 상품 저장
    @Transactional
    public Product insert(City city, Category category, SubCategory subCategory, ProductDto productDto) {
        Product product = Product.createProduct(city, category, subCategory, productDto);
        productRepository.save(product);
        return product;
    }

    //전체 상품 조회
    @Transactional(readOnly = true)
    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    //아이디로 상품 조회
    @Transactional(readOnly = true)
    public Product findOne(Long id) {
        return productRepository.findOne(id);
    }

    //이름으로 상품 조회
    @Transactional(readOnly = true)
    public List<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    //상품 클릭 횟수 증가
    @Transactional
    public void addClicked(Long productId) {
        Product product = productRepository.findOne(productId);
        int clicked = product.getClicked();
        product.setClicked(++clicked);
    }
}
