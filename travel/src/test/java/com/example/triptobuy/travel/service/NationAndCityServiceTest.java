//package com.example.triptobuy.travel.service;
//
//import com.example.triptobuy.travel.domain.City;
//import com.example.triptobuy.travel.domain.Nation;
//import com.example.triptobuy.travel.dto.CityDto;
//import com.example.triptobuy.travel.dto.NationDto;
//import com.example.triptobuy.travel.repository.CityRepository;
//import com.example.triptobuy.travel.repository.MemberRepository;
//import com.example.triptobuy.travel.repository.NationRepository;
//import com.example.triptobuy.travel.repository.SearchRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional()
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // test 순서 명시 가능 @Order
//class NationAndCityServiceTest {
//
//    @Autowired
//    CityService cityService;
//    @Autowired
//    CityRepository cityRepository;
//    @Autowired
//    NationService nationService;
//    @Autowired
//    NationRepository nationRepository;
//    @Autowired
//    SearchService searchService;
//    @Autowired
//    SearchRepository searchRepository;
//
//
//    /**
//     * (어차피 직접 등록하지 않을 예정이니 아직까진 예외처리 필요 X)
//     * 1. 나라, 도시 등록
//     * 2. 나라, 도시 목록
//     * 3. 나라, 도시 검색 (이름)
//     * 4. 나라, 도시 검색 횟수 증가
//     */
//
//    // 나라, 도시 등록
//    @BeforeEach
//    @Rollback(value = false)
//    public void beforeEach() {
//        NationDto nationDto = createNationDto("미국", "image/usa", "미국에 대한 내용");
//        Nation nation = nationService.join(nationDto);
//        CityDto cityDto1 = createCityDto("워싱턴", "image/워싱턴", "워싱턴에 대한 내용");
//        CityDto cityDto2 = createCityDto("뉴욕", "image/뉴욕", "뉴욕에 대한 내용");
//        CityDto cityDto3 = createCityDto("LA", "image/LA", "LA에 대한 내용");
//
//        cityService.join(nation, cityDto1);
//        cityService.join(nation, cityDto2);
//        cityService.join(nation, cityDto3);
//    }
//
//    public NationDto createNationDto(String name, String picture, String detail) {
//        NationDto nationDto = new NationDto();
//        nationDto.setName(name);
//        nationDto.setPicture(picture);
//        nationDto.setDetail(detail);
//        return nationDto;
//    }
//
//    public CityDto createCityDto(String name, String picture, String detail) {
//        CityDto cityDto = new CityDto();
//        cityDto.setName(name);
//        cityDto.setPicture(picture);
//        cityDto.setDetail(detail);
//        return cityDto;
//    }
//
//    //나라, 도시 목록
//    @Test
//    @DisplayName("나라, 목록 확인")
//    @Order(1)
//    public void test1() throws Exception {
//        //given
//
//        //when
//
//        //then
//
//    }
//}