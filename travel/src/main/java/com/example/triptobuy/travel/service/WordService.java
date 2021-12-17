package com.example.triptobuy.travel.service;

import com.example.triptobuy.travel.domain.City;
import com.example.triptobuy.travel.domain.Nation;
import com.example.triptobuy.travel.domain.Word;
import com.example.triptobuy.travel.dto.WordDto;
import com.example.triptobuy.travel.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class WordService {

    private final WordRepository wordRepository;

    //나라 단어 저장
    @Transactional
    public void insertNationWord(Nation nation, WordDto wordDto) {
        Word word = Word.createNationWord(nation, wordDto);
        wordRepository.save(word);
    }

    //도시 단어 저장
    @Transactional
    public void insertCityWord(City city, WordDto wordDto) {
        Word word = Word.createCityWord(city, wordDto);
        wordRepository.save(word);
    }

    //단어로 나라 조회
    @Transactional(readOnly = true)
    public List<Nation> findNationByWord(String word) {
        return wordRepository.findNationByWord(word);
    }

    //단어로 도시 조회
    @Transactional(readOnly = true)
    public List<City> findCityByWord(String word) {
        return wordRepository.findCityByWord(word);
    }
}
