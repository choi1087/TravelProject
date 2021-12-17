package com.example.triptobuy.travel.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_MB_SEARCH_CITY")
@Getter
@Setter
public class SearchCity extends Timestamped{

    @Id
    @GeneratedValue
    @Column(name = "SER_CTY_ID")
    private Long id;

    //연관관계 주인
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEM_ID")
    private Member member;

    @Column(name = "CTY_NM")
    private String cityName;

    //==연관관계 매서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getSearchCityList().add(this);
    }

    public static SearchCity createSearchCity(Member member, String cityName) {
        SearchCity searchCity = new SearchCity();
        searchCity.setMember(member);
        searchCity.setCityName(cityName);
        return searchCity;
    }
}

