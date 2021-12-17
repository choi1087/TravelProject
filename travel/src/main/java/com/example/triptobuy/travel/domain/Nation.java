package com.example.triptobuy.travel.domain;

import com.example.triptobuy.travel.dto.NationDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "TB_DM_NATION")
@Getter
@Setter
public class Nation {

    @Id
    @GeneratedValue
    @Column(name = "NTN_ID")
    private Long id;

    @Column(name = "NTN_NM")
    private String name;

    @Column(name="NTN_PICR")
    private String picture;

    @Column(name = "NTN_DTL")
    private String detail;

    @Column(name = "NTN_CLICKED")
    private int clicked;

    @JsonIgnore
    @Column(name = "NTN_WORD")
    @OneToMany(mappedBy = "nation")
//    private List<Word> wordList = new ArrayList<>();
    private Set<Word> wordList = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "nation", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<City> cityList = new ArrayList<>();

    //== 생성 메서드 ==/
    public static Nation createNation(NationDto nationDto) {
        Nation nation = new Nation();
        nation.setName(nationDto.getName());
        nation.setPicture(nationDto.getPicture());
        nation.setDetail(nationDto.getDetail());
        nation.setClicked(0);
        return nation;
    }
}
