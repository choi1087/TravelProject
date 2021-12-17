package com.example.triptobuy.travel.repository;

import com.example.triptobuy.travel.domain.SubCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SubCategoryRepository {

    private final EntityManager em;

    //서브 카테고리 저장
    public void save(SubCategory subCategory) {
        em.persist(subCategory);
    }

    //아이디(PK)로 서브 카테고리 저장
    public SubCategory findOne(Long id) {
        return em.find(SubCategory.class, id);
    }

    //서브 카테고리 목록
    public List<SubCategory> findAll() {
        return em.createQuery("select s from SubCategory s", SubCategory.class)
                .getResultList();
    }

    //서브 카테고리 이름으로 카테고리 정보 찾기
    public List<SubCategory> findByName(String name) {
        return em.createQuery("select s from SubCategory s where s.name =:name")
                .setParameter("name", name)
                .getResultList();
    }
}
