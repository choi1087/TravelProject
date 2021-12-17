package com.example.triptobuy.travel.repository;

import com.example.triptobuy.travel.domain.Nation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class NationRepository {

    //엔티티 매니저 주입
    private final EntityManager em;

    //나라 저장
    public void save(Nation nation) {
        em.persist(nation);
    }

    //아이디(PK)로 나라 찾기
    public Nation findOne(Long id) {
        return em.find(Nation.class, id);
    }

    //나라 목록
    public List<Nation> findAll() {
        return em.createQuery("select n from Nation n", Nation.class)
                .getResultList();
    }

    //나라 목록(클릭횟수 많은 순으로)
    public List<Nation> findAllOrderByClicked(Sort clicked) {
        return em.createQuery("select n from Nation n order by n.clicked desc ", Nation.class)
                .getResultList();
    }

    //나라 이름으로 나라 정보 찾기
    public List<Nation> findByName(String name) {
        return em.createQuery("select n from Nation n where n.name =:name", Nation.class)
                .setParameter("name", name)
                .getResultList();
    }

}
