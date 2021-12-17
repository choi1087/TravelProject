package com.example.triptobuy.travel.controller;

import com.example.triptobuy.travel.domain.City;
import com.example.triptobuy.travel.domain.Nation;
import com.example.triptobuy.travel.dto.CityDto;
import com.example.triptobuy.travel.service.CityService;
import com.example.triptobuy.travel.service.NationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;
    private final NationService nationService;

    /**
     * @return 도시 전체 리스트
     */
    @GetMapping("/cityList")
    public List<City> getCityList() {
        return cityService.findCities();
    }

    /**
     * @param nationName
     * @return 해당 나라에 속한 도시 리스트
     */
    @GetMapping("/cities/nation")
    public List<City> getCityInNation(@RequestParam String nationName) {
        return cityService.findByNationName(nationName);
    }

    /**
     * @param cityName
     * @return cityName 에 해당 도시
     */
    @GetMapping("/cities")
    public City getCity(@RequestParam String cityName) {
        return cityService.findByName(cityName).get(0);
    }

    /**
     * @param nationName
     * @param cityDto
     * @return nationName 나라에 속한 cityDto 에 대한 도시 추가
     */
    @PostMapping("/cities")
    public City postCity(@RequestParam String nationName, @RequestBody CityDto cityDto) {
        Nation nation = nationService.findByName(nationName).get(0);
        return cityService.join(nation, cityDto);
    }

    /**
     * @param cityName
     * @return 해당 도시에 대한 클릭 횟수 증가
     */
    @PatchMapping("/clicked/cities")
    public void addCityClicked(@RequestParam String cityName) {
        cityService.addClicked(cityName);
    }

}
