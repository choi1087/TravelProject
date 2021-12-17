package com.example.triptobuy.travel.controller;

import com.example.triptobuy.travel.domain.Category;
import com.example.triptobuy.travel.domain.City;
import com.example.triptobuy.travel.domain.Product;
import com.example.triptobuy.travel.domain.SubCategory;
import com.example.triptobuy.travel.dto.ProductDto;
import com.example.triptobuy.travel.service.CategoryService;
import com.example.triptobuy.travel.service.CityService;
import com.example.triptobuy.travel.service.ProductService;
import com.example.triptobuy.travel.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CityService cityService;
    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;

    /**
     * @return 상품 전체 리스트
     */
    @GetMapping("/productList")
    public List<Product> getProductList() {
        return productService.findProducts();
    }

    /**
     * @param productName
     * @return productName 에 해당 상품들
     */
    @GetMapping("/products")
    public List<Product> getProductByName(@RequestParam String productName) {
        return productService.findByName(productName);
    }

    /**
     * @param productId
     * @return 특정 상품 정보
     */
    @GetMapping("/search/products")
    public Product getProductById(@RequestParam Long productId) {
        return productService.findOne(productId);
    }


    /**
     * @param cityName
     * @param categoryName
     * @param productDto
     * @return 상품 등록(상품 도시, 카테고리 포함)
     */
    @PostMapping("/products")
    public Product postProduct(@RequestParam String cityName, @RequestParam String categoryName, @RequestParam String subCategoryName, @RequestBody ProductDto productDto) {
        City city = cityService.findByName(cityName).get(0);
        Category category = categoryService.findByName(categoryName).get(0);
        SubCategory subCategory = subCategoryService.findByName(subCategoryName).get(0);
        return productService.insert(city, category, subCategory, productDto);
    }

    /**
     * @param productId
     * @return productName 상품에 대한 클릭 횟수 증가
     */
    @PatchMapping("/clicked/products")
    public void addProductClicked(@RequestParam Long productId) {
        productService.addClicked(productId);
    }
}
