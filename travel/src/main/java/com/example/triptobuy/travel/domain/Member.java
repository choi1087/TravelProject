package com.example.triptobuy.travel.domain;

import com.example.triptobuy.travel.dto.MemberDto;
import com.example.triptobuy.travel.exception.ApiRequestException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "TB_MB_MEMBER")
public class Member extends Timestamped{

    @Id
    @GeneratedValue
    @Column(name = "MEM_ID")
    private Long id;

    @Column(name = "MEM_NM")
    private String name;

    @Column(name = "MEM_EMAIL")
    private String email;

    @Column(name = "MEM_PW")
    private String pw;

    @Column(name = "MEM_GNDR")
    private char gender;

    @Column(name = "MEM_AGE")
    private int age;

    @Column(name = "MEM_STATUS")
    private char status;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<SearchNation> searchNationList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<SearchCity> searchCityList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<SearchProduct> searchProductList = new ArrayList<>();

    //== 생성 메서드 ==//
    public static Member createMember(MemberDto memberDto) {
        // 입력값 검증
        validateMemberDto(memberDto);

        // 회원 저장
        Member member = new Member();
        member.setName(memberDto.getName());
        member.setEmail(memberDto.getEmail());
        member.setPw(memberDto.getPw());
        member.setGender(memberDto.getGender());
        member.setAge(memberDto.getAge());
        member.setStatus(memberDto.getStatus());
        return member;
    }

    public static void validateMemberDto(MemberDto memberDto) {
        if (memberDto.getName() == null || memberDto.getName().isEmpty()) {
            throw new ApiRequestException("이름은 필수 입력 값입니다.");
        }
        if (memberDto.getEmail() == null || memberDto.getEmail().isEmpty()) {
            throw new ApiRequestException("이메일은 필수 입력 값입니다.");
        }
        if (memberDto.getPw() == null || memberDto.getPw().isEmpty()) {
            throw new ApiRequestException("비밀번호는 필수 입력 값입니다.");
        }
        if (memberDto.getPw().length() < 10) {
            throw new ApiRequestException("비밀번호는 최소 10자리입니다.");
        }
        if (memberDto.getGender() == ' ') {
            throw new ApiRequestException("성별을 입력해주세요.");
        }
        if (memberDto.getAge() < 0) {
            throw new ApiRequestException("나이를 잘못 입력하셨습니다.");
        }
    }
}
