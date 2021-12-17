package com.example.triptobuy.travel.repository;

import com.example.triptobuy.travel.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final EntityManager em;

    //상품 저장
    public void save(Product product) {
        em.persist(product);
    }

    //아이디(PK)로 상품 찾기
    public Product findOne(Long id) {
        return em.find(Product.class, id);
    }

    //상품 목록
    public List<Product> findAll() {
        return em.createQuery("select p from Product p", Product.class)
                .getResultList();
    }

    //상품이름으로 상품 정보 찾기
    public List<Product> findByName(String name) {
        return em.createQuery("select p from Product p where p.name like concat('%', :name,'%') ")
                .setParameter("name", name)
                .getResultList();
    }

}
