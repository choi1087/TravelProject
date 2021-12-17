package com.example.triptobuy.travel.controller;

import com.example.triptobuy.travel.domain.City;
import com.example.triptobuy.travel.domain.Nation;
import com.example.triptobuy.travel.dto.WordDto;
import com.example.triptobuy.travel.service.CityService;
import com.example.triptobuy.travel.service.NationService;
import com.example.triptobuy.travel.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WordController {

    private final NationService nationService;
    private final CityService cityService;
    private final WordService wordService;

    //나라 단어 저장
    @PostMapping("/words/nation")
    public void postNationWord(@RequestParam String nationName, @RequestParam WordDto word) {
        Nation nation = nationService.findByName(nationName).get(0);
        wordService.insertNationWord(nation, word);
    }

    //도시 단어 저장
    @PostMapping("/words/city")
    public void postCityWord(@RequestParam String cityName, @RequestParam WordDto word) {
        City city = cityService.findByName(cityName).get(0);
        wordService.insertCityWord(city, word);
    }

    //단어로 나라 검색
    @GetMapping("/words/nation")
    public List<Nation> getNations(@RequestParam String word) {
        return wordService.findNationByWord(word);
    }

    //단어로 도시 검색
    @GetMapping("/words/city")
    public List<City> getCities(@RequestParam String word) {
        return wordService.findCityByWord(word);
    }
}
