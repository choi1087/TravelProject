package com.example.triptobuy.travel.domain;

import com.example.triptobuy.travel.dto.WordDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "WORD")
@Getter
@Setter
public class Word {
    @Id @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "WORD")
    private String word;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NTN_ID")
    private Nation nation;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CTY_ID")
    private City city;


    //== 연관관계 메서드(나라) ==//
    public void setNation(Nation nation) {
        this.nation = nation;
        nation.getWordList().add(this);
    }

    //== 연관관계 메서드(도시) ==//
    public void setCity(City city) {
        this.city = city;

    }

    //== 생성 메서드(나라 단어) ==//
    public static Word createNationWord(Nation nation, WordDto wordDto) {
        Word word = new Word();
        word.setNation(nation);
        word.setWord(wordDto.getWord());
        return word;
    }

    //== 생성 메서드(도시 단어) ==//
    public static Word createCityWord(City city, WordDto wordDto) {
        Word word = new Word();
        word.setCity(city);
        word.setWord(wordDto.getWord());
        return word;
    }
}
