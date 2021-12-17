package com.example.triptobuy.travel.service;

import com.example.triptobuy.travel.domain.City;
import com.example.triptobuy.travel.domain.Nation;
import com.example.triptobuy.travel.dto.CityDto;
import com.example.triptobuy.travel.exception.ApiRequestException;
import com.example.triptobuy.travel.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class CityService {

    private final CityRepository cityRepository;

    //도시 저장
    @Transactional
    public City join(Nation nation, CityDto cityDto) {

        //중복 도시 검증, 도시 이름으로 확인
        validateDuplicateCity(cityDto);

        //중복 도시가 없다면
        City city = City.createCity(nation, cityDto);
        cityRepository.save(city);
        return city;
    }

    private void validateDuplicateCity(CityDto cityDto) {
        List<City> findCities = cityRepository.findByName(cityDto.getName());
        if (!findCities.isEmpty()) {
            throw new ApiRequestException("이미 존재하는 도시입니다.");
        }
    }

    //전체 도시 조회
    @Transactional(readOnly = true)
    public List<City> findCities() {
        return cityRepository.findAll();
    }

    //아이디로 도시 조회
    @Transactional(readOnly = true)
    public City findOne(Long cityId) {
        return cityRepository.findOne(cityId);
    }

    //이름으로 도시 조회
    @Transactional(readOnly = true)
    public List<City> findByName(String name) {
        return cityRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public List<City> findByNationName(String nationName) {
        return cityRepository.findCitiesByNationName(nationName);
    }

    //도시 클릭 횟수 증가
    @Transactional
    public City addClicked(String cityName) {
        City city = cityRepository.findByName(cityName).get(0);
        int clicked = city.getClicked();
        city.setClicked(++clicked);
        return city;
    }
}
