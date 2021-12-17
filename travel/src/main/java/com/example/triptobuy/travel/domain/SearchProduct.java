package com.example.triptobuy.travel.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_MB_SEARCH_PRODUCT")
@Getter
@Setter
public class SearchProduct extends Timestamped{
    @Id
    @GeneratedValue
    @Column(name = "SER_PRD_ID")
    private Long id;

    //연관관계 주인
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEM_ID")
    private Member member;

    @Column(name = "PRD_NM")
    private String productName;

    //==연관관계 매서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getSearchProductList().add(this);
    }

    public static SearchProduct createSearchProduct(Member member, String productName) {
        SearchProduct searchProduct = new SearchProduct();
        searchProduct.setMember(member);
        searchProduct.setProductName(productName);
        return searchProduct;
    }

}
