package com.example.triptobuy.travel.domain;

import com.example.triptobuy.travel.dto.SubCategoryDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_DM_SUBCATEGORY")
@Getter
@Setter
public class SubCategory {

    @Id
    @GeneratedValue
    @Column(name = "SUBC_ID")
    private Long id;

    @Column(name = "SUBC_NM")
    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "CTGR_ID")
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy = "subCategory", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Product> productList = new ArrayList<>();

    //== 연관관계 메서드 ==///
    public void setCategory(Category category) {
        this.category = category;
        category.getSubCategoryList().add(this);
    }

    //== 생성 메서드 ==//
    public static SubCategory createSubCategory(Category category, SubCategoryDto subCategoryDto) {
        SubCategory subCategory = new SubCategory();
        subCategory.setCategory(category);
        subCategory.setName(subCategoryDto.getName());
        return subCategory;
    }
}
