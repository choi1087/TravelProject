package com.example.triptobuy.travel.service;

import com.example.triptobuy.travel.domain.Category;
import com.example.triptobuy.travel.domain.SubCategory;
import com.example.triptobuy.travel.dto.CategoryDto;
import com.example.triptobuy.travel.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    //카테고리 저장
    @Transactional
    public Category insert(CategoryDto categoryDto) {
        Category category = Category.createCategory(categoryDto);
        categoryRepository.save(category);
        return category;
    }

    //전체 카테고리 조회
    @Transactional(readOnly = true)
    public List<Category> findCategories() {
        return categoryRepository.findAll();
    }

    //아이디로 카테고리 조회
    @Transactional(readOnly = true)
    public Category findOne(Long id) {
        return categoryRepository.findOne(id);
    }

    //이름으로 카테고리 조회
    @Transactional(readOnly = true)
    public List<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }
}
