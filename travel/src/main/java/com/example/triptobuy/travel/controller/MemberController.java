package com.example.triptobuy.travel.controller;

import com.example.triptobuy.travel.domain.Member;
import com.example.triptobuy.travel.domain.SearchCity;
import com.example.triptobuy.travel.domain.SearchNation;
import com.example.triptobuy.travel.domain.SearchProduct;
import com.example.triptobuy.travel.dto.MemberDto;
import com.example.triptobuy.travel.service.MemberService;
import com.example.triptobuy.travel.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final SearchService searchService;

    /**
     * @return 전체 회원 리스트
     */
    @GetMapping("/memberList")
    public List<Member> memberList() {
        return memberService.findMembers();
    }

    /**
     * @param memberName
     * @return memberName 에 해당하는 회원
     */
    @GetMapping("/members/name")
    public Member getMemberByName(@RequestParam String memberName) {
        return memberService.findByName(memberName).get(0);
    }

    /**
     * @param email
     * @param pw
     * @return memberEmail, pw 에 해당하는 회원
     */
    @GetMapping("/members/login")
    public Member getMemberByEmail(@RequestParam String email, @RequestParam String pw) {
        Member member = memberService.findByEmail(email).get(0);
        memberService.checkPw(member.getPw(), pw);
        return member;
    }

    /**
     * @param memberDto
     * @return 회원가입
     */
    @PostMapping("/members")
    public Member postMember(@RequestBody @Valid MemberDto memberDto) {
        return memberService.join(memberDto);
    }

    /**
     * @param memberId
     * @param productName 해당 회원의 해당 상품 검색 내역 추가
     */
    @GetMapping("/search/product")
    public void searchProduct(@RequestParam Long memberId, @RequestParam String productName) {
        searchService.insertSearchProduct(memberId, productName);
    }

    /**
     * @param memberId
     * @return 해당 멤버의 전체 상품 검색 기록
     */
    @GetMapping("/history/products")
    public List<SearchProduct> getSearchProductListById(@RequestParam Long memberId) {
        return searchService.findSearchProducts(memberId);
    }

    /**
     * @param memberId
     * @param nationName 해당 회원의 해당 나라 검색 내역 추가
     */
    @GetMapping("/search/nations")
    public void searchNation(@RequestParam Long memberId, @RequestParam String nationName) {
        searchService.insertSearchNation(memberId, nationName);
    }

    /**
     * @param memberId
     * @return 해당 멤버의 전체 나라 검색 기록
     */
    @GetMapping("/history/nations")
    public List<SearchNation> getSearchNationListById(@RequestParam Long memberId) {
        return searchService.findSearchNations(memberId);
    }

    /**
     * @param memberId
     * @param cityName 해당 회원의 해당 도시 검색 내역 추가
     */
    @GetMapping("/search/cities")
    public void searchCity(@RequestParam Long memberId, @RequestParam String cityName) {
        searchService.insertSearchCity(memberId, cityName);
    }

    /**
     * @param memberId
     * @return 해당 멤버의 전체 도시 검색 기록
     */
    @GetMapping("/history/cities")
    public List<SearchCity> getSearchCityListById(@RequestParam Long memberId) {
        return searchService.findSearchCities(memberId);
    }

    /**
     * 회원 탈퇴
     * @param id
     */
    @DeleteMapping("/members/{id}")
    public void deleteMember(@PathVariable("id") Long id) {
        memberService.deleteMember(id);
    }

    /**
     * 상품 검색 기록 삭제
     * @param searchId
     */
    @DeleteMapping("/search/{searchId}")
    public void deleteSearchProduct(@PathVariable("searchId") Long searchId) {
        searchService.deleteSearchProduct(searchId);
    }
}
