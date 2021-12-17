package com.example.triptobuy.travel.repository;

import com.example.triptobuy.travel.domain.City;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CityRepository {

    private final EntityManager em;

    //도시 저장
    public void save(City city) {
        em.persist(city);
    }

    //아이디(PK)로 도시 찾기
    public City findOne(Long id) {
        return em.find(City.class, id);
    }

    //도시 목록
    public List<City> findAll() {
        return em.createQuery("select c from City c", City.class)
                .getResultList();
    }

    //도시이름으로 도시 정보 찾기
    public List<City> findByName(String name) {
        return em.createQuery("select c from City c where c.name =:name")
                .setParameter("name", name)
                .getResultList();
    }

    //해당 나라에 속한 도시 정보 찾기
    public List<City> findCitiesByNationName(String nationName) {
        return em.createQuery("select c from City c where c.nation.name =:nationName")
                .setParameter("nationName", nationName)
                .getResultList();
    }
}
