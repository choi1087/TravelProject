package com.example.triptobuy.travel.domain;

import com.example.triptobuy.travel.dto.ProductDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "TB_DM_PRODUCT")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "PRD_ID")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CTY_ID")
    private City city;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CTGR_ID")
    private Category category;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCTGR_ID")
    private SubCategory subCategory;

    @Column(name = "PRD_NM")
    private String name;

    @Column(name = "PRD_PICR")
    private String picture;

    @Column(name = "PRD_PRICE")
    private int price;

    @Column(name = "PRD_CLICKED")
    private int clicked;

    //== 연관관계 메서드 ==//
    public void setCity(City city) {
        this.city = city;
        city.getProductList().add(this);
    }

    public void setCategory(Category category) {
        this.category = category;
        category.getProductList().add(this);
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
        subCategory.getProductList().add(this);
    }

    //== 생성 메서드 ==//
    public static Product createProduct(City city, Category category, SubCategory subCategory,  ProductDto productDto) {
        Product product = new Product();
        product.setCity(city);
        product.setCategory(category);
        product.setSubCategory(subCategory);
        product.setName(productDto.getName());
        product.setPicture(productDto.getPicture());
        product.setPrice(productDto.getPrice());
        product.setClicked(0);
        return product;
    }
}
