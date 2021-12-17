package com.example.triptobuy.travel.domain;

import com.example.triptobuy.travel.dto.CityDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_DM_CITY")
@Getter
@Setter
public class City {

    @Id
    @GeneratedValue
    @Column(name = "CTY_ID")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NTN_ID")
    private Nation nation;

    @Column(name = "CTY_NM")
    private String name;

    @Column(name = "CTY_PICR")
    private String picture;

    @Column(name = "CTY_DTL")
    private String detail;

    @Column(name = "CTY_CLICKED")
    private int clicked;

    @JsonIgnore
    @OneToMany(mappedBy = "city", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Product> productList = new ArrayList<>();

    @JsonIgnore
    @Column(name = "CTY_WORD")
    @OneToMany(mappedBy = "city")
    private List<Word> wordList = new ArrayList<>();

    //== 연관관계 메서드 ==//
    public void setNation(Nation nation) {
        this.nation = nation;
        nation.getCityList().add(this);
    }

    //== 생성 메서드 ==/
    public static City createCity(Nation nation, CityDto cityDto) {
        City city = new City();
        city.setNation(nation);
        city.setName(cityDto.getName());
        city.setPicture(cityDto.getPicture());
        city.setDetail(cityDto.getDetail());
        city.setClicked(0);
        return city;
    }
}
