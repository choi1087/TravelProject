package com.example.triptobuy.travel.repository;

import com.example.triptobuy.travel.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SearchRepository {

    private final EntityManager em;

    //나라 검색 기록 저장
    public void saveSearchNations(SearchNation searchNation) {
        em.persist(searchNation);
    }

    //도시 검색 기록 저장
    public void saveSearchCities(SearchCity searchCity) {
        em.persist(searchCity);
    }

    //상품 검색 기록 저장
    public void saveSearchProducts(SearchProduct searchProduct) {
        em.persist(searchProduct);
    }

    //회원 아이디로 나라 검색 기록 찾기
    public List<SearchNation> findSearchNationsById(Long memberId) {
        return em.createQuery("select n from SearchNation n where n.member.id =:memberId", SearchNation.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    //회원 아이디로 도시 검색 기록 찾기
    public List<SearchCity> findSearchCitiesById(Long memberId) {
        return em.createQuery("select c from SearchCity c where c.member.id =:memberId", SearchCity.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    //회원 아이디로 상품 검색 기록 찾기
    public List<SearchProduct> findSearchProductsById(Long memberId) {
        return em.createQuery("select p from SearchProduct p where p.member.id =:memberId", SearchProduct.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    //나라 검색 기록 삭제
    public void deleteSearchNation(Long searchId) {
        SearchNation searchNation = em.find(SearchNation.class, searchId);
        em.remove(searchNation);
    }

    //도시 검색 기록 삭제
    public void deleteSearchCity(Long searchId) {
        SearchCity searchCity = em.find(SearchCity.class, searchId);
        em.remove(searchCity);
    }

    //상품 검색 기록 삭제
    public void deleteSearchProduct(Long searchId) {
        SearchProduct searchProduct = em.find(SearchProduct.class, searchId);
        em.remove(searchProduct);
    }

    //상품 검색 전체 기록 - test용도
    public List<SearchProduct> findAll() {
        return em.createQuery("select sp from SearchProduct sp", SearchProduct.class)
                .getResultList();
    }
}
