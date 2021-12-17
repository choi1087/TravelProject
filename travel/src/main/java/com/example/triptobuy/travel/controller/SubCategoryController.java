package com.example.triptobuy.travel.controller;

import com.example.triptobuy.travel.domain.Category;
import com.example.triptobuy.travel.domain.SubCategory;
import com.example.triptobuy.travel.dto.SubCategoryDto;
import com.example.triptobuy.travel.service.CategoryService;
import com.example.triptobuy.travel.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SubCategoryController {

    private final SubCategoryService subCategoryService;
    private final CategoryService categoryService;

    /**
     * @return 서브 카테고리 전체 리스트
     */
    @GetMapping("/subCategoryList")
    public List<SubCategory> getSubCategoryList() {
        return subCategoryService.findSubCategories();
    }

    /**
     * @param subCategoryName
     * @return subCategoryName 에 해당하는 서브 카테고리
     */
    @GetMapping("/subCategories")
    public SubCategory getSubCategory(@RequestParam String subCategoryName) {
        return subCategoryService.findByName(subCategoryName).get(0);
    }

    /**
     * @param subCategoryDto
     * @return 서브 카테고리 등록
     */
    @PostMapping("/subCategories")
    public SubCategory postSubCategory(@RequestParam String categoryName, @RequestBody SubCategoryDto subCategoryDto) {
        Category category = categoryService.findByName(categoryName).get(0);
        return subCategoryService.insert(category, subCategoryDto);
    }
}
