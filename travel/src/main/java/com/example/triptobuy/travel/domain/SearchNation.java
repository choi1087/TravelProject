package com.example.triptobuy.travel.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_MB_SEARCH_NATION")
@Getter
@Setter
public class SearchNation extends Timestamped{

    @Id
    @GeneratedValue
    @Column(name = "SER_NTN_ID")
    private Long id;

    //연관관계 주인
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEM_ID")
    private Member member;

    @Column(name = "NTN_NM")
    private String nationName;

    //==연관관계 매서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getSearchNationList().add(this);
    }

    public static SearchNation createSearchNation(Member member, String nationName) {
        SearchNation searchNation = new SearchNation();
        searchNation.setMember(member);
        searchNation.setNationName(nationName);
        return searchNation;
    }
}
