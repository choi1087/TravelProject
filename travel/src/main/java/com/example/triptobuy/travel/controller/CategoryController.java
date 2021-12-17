package com.example.triptobuy.travel.controller;

import com.example.triptobuy.travel.domain.Category;
import com.example.triptobuy.travel.domain.SubCategory;
import com.example.triptobuy.travel.dto.CategoryDto;
import com.example.triptobuy.travel.service.CategoryService;
import com.example.triptobuy.travel.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;

    /**
     * @return 카테고리 전체 리스트
     */
    @GetMapping("/categoryList")
    public List<Category> getCategoryList() {
        return categoryService.findCategories();
    }

    /**
     * @param categoryName
     * @return categoryName 에 해당 카테고리
     */
    @GetMapping("/categories")
    public Category getCategory(@RequestParam String categoryName) {
        return categoryService.findByName(categoryName).get(0);
    }

    /**
     * @param categoryDto
     * @return
     */
    @PostMapping("/categories")
    public Category postCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.insert(categoryDto);
    }
}
