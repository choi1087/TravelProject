package com.example.triptobuy.travel.repository;

import com.example.triptobuy.travel.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {

    private final EntityManager em;

    //카테고리 저장
    public void save(Category category) {
        em.persist(category);
    }

    //아이디(PK)로 카테고리 찾기
    public Category findOne(Long id) {
        return em.find(Category.class, id);
    }

    //카테고리 목록
    public List<Category> findAll() {
        return em.createQuery("select c from Category c", Category.class)
                .getResultList();
    }

    //카레고리 이름으로 카테고리 정보 찾기
    public List<Category> findByName(String name) {
        return em.createQuery("select c from Category c where c.name =:name")
                .setParameter("name", name)
                .getResultList();
    }
}
