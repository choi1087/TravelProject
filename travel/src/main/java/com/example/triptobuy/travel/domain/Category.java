package com.example.triptobuy.travel.domain;

import com.example.triptobuy.travel.dto.CategoryDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_DM_CATEGORY")
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "CTGR_ID")
    private Long id;

    @Column(name = "CTGR_NM")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<SubCategory> subCategoryList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Product> productList = new ArrayList<>();


    //== 생성 메서드 ==//
    public static Category createCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        return category;
    }
}
