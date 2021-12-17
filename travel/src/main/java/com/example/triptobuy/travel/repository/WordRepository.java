package com.example.triptobuy.travel.repository;

import com.example.triptobuy.travel.domain.City;
import com.example.triptobuy.travel.domain.Nation;
import com.example.triptobuy.travel.domain.Word;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class WordRepository {

    private final EntityManager em;

    public void save(Word word) {
        em.persist(word);
    }

    //단어로 나라 찾기
    public List<Nation> findNationByWord(String word) {
        return em.createQuery("select w.nation from Word w where w.word=:word")
                .setParameter("word", word)
                .getResultList();
    }

    //단어로 도시 찾기
    public List<City> findCityByWord(String word) {
        return em.createQuery("select w.city from Word w where w.word=:word")
                .setParameter("word", word)
                .getResultList();
    }
}
