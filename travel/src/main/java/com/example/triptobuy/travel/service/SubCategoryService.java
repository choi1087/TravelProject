package com.example.triptobuy.travel.service;

import com.example.triptobuy.travel.domain.Category;
import com.example.triptobuy.travel.domain.SubCategory;
import com.example.triptobuy.travel.dto.SubCategoryDto;
import com.example.triptobuy.travel.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;

    //서브 카테고리 저장
    @Transactional
    public SubCategory insert(Category category, SubCategoryDto subCategoryDto) {
        SubCategory subCategory = SubCategory.createSubCategory(category, subCategoryDto);
        subCategoryRepository.save(subCategory);
        return subCategory;
    }

    //전체 서브 카테고리 조회
    @Transactional(readOnly = true)
    public List<SubCategory> findSubCategories() {
        return subCategoryRepository.findAll();
    }

    //아이디로 서브 카테고리 조회
    @Transactional(readOnly = true)
    public SubCategory findOne(Long id) {
        return subCategoryRepository.findOne(id);
    }

    //이름으로 서브 카테고리 조회
    @Transactional(readOnly = true)
    public List<SubCategory> findByName(String name) {
        return subCategoryRepository.findByName(name);
    }
}
