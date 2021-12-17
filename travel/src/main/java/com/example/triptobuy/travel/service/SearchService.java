package com.example.triptobuy.travel.service;

import com.example.triptobuy.travel.domain.Member;
import com.example.triptobuy.travel.domain.SearchCity;
import com.example.triptobuy.travel.domain.SearchNation;
import com.example.triptobuy.travel.domain.SearchProduct;
import com.example.triptobuy.travel.repository.MemberRepository;
import com.example.triptobuy.travel.repository.SearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class SearchService {

    private final MemberRepository memberRepository;
    private final SearchRepository searchRepository;

    //각 멤버에 맞춰 나라 검색기록 삽입
    @Transactional
    public void insertSearchNation(Long memberId, String nationName) {

        // 중복 나라 검색 기록이 있다면, 기존 데이터 삭제
        List<SearchNation> searchNations = findSearchNations(memberId);
        for (SearchNation searchNation : searchNations) {
            if (searchNation.getNationName().equals(nationName)) {
                deleteSearchNation(searchNation.getId());
                break;
            }
        }

        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        //검색기록 생성
        SearchNation searchNation = SearchNation.createSearchNation(member, nationName);
        searchRepository.saveSearchNations(searchNation);
    }

    //각 멤버에 맞춰 나라 도시기록 삽입
    @Transactional
    public void insertSearchCity(Long memberId, String cityName) {

        // 중복된 도시 검색 기록이 있다면, 기존 데이터 삭제
        List<SearchCity> searchCities = findSearchCities(memberId);
        for (SearchCity searchCity : searchCities) {
            if (searchCity.getCityName().equals(cityName)) {
                deleteSearchCity(searchCity.getId());
                break;
            }
        }

        Member member = memberRepository.findOne(memberId);
        SearchCity searchCity = SearchCity.createSearchCity(member, cityName);
        searchRepository.saveSearchCities(searchCity);
    }

    //각 멤버에 맞춰 상품 검색기록 삽입
    @Transactional
    public void insertSearchProduct(Long memberId, String productName) {

        // 중복된 상품 검색 기록이 있다면, 기존 데이터 삭제
        List<SearchProduct> searchProducts = findSearchProducts(memberId);
        for (SearchProduct searchProduct : searchProducts) {
            if (searchProduct.getProductName().equals(productName)) {
                deleteSearchProduct(searchProduct.getId());
                break;
            }
        }

        Member member = memberRepository.findOne(memberId);
        SearchProduct searchProduct = SearchProduct.createSearchProduct(member, productName);
        searchRepository.saveSearchProducts(searchProduct);
    }

    //해당 멤버의 나라 검색 기록
    @Transactional(readOnly = true)
    public List<SearchNation> findSearchNations(Long memberId) {
        return searchRepository.findSearchNationsById(memberId);
    }

    //해당 멤버의 도시 검색 기록
    @Transactional(readOnly = true)
    public List<SearchCity> findSearchCities(Long memberId) {
        return searchRepository.findSearchCitiesById(memberId);
    }

    //해당 멤버의 상품 검색 기록
    @Transactional(readOnly = true)
    public List<SearchProduct> findSearchProducts(Long memberId) {
        return searchRepository.findSearchProductsById(memberId);
    }

    //나라 검색 기록 삭제
    @Transactional
    public void deleteSearchNation(Long searchId) {
        searchRepository.deleteSearchNation(searchId);
    }

    //도시 검색 기록 삭제
    @Transactional
    public void deleteSearchCity(Long searchId) {
        searchRepository.deleteSearchCity(searchId);
    }

    //상품 검색 기록 삭제
    @Transactional
    public void deleteSearchProduct(Long searchId) {
        searchRepository.deleteSearchProduct(searchId);
    }
}
