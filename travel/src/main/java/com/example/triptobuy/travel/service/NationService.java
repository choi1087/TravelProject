package com.example.triptobuy.travel.service;

import com.example.triptobuy.travel.domain.Nation;
import com.example.triptobuy.travel.dto.NationDto;
import com.example.triptobuy.travel.exception.ApiRequestException;
import com.example.triptobuy.travel.repository.NationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class NationService {

    private final NationRepository nationRepository;

    //나라 저장
    @Transactional
    public Nation join(NationDto nationDto) {

        //중복 나라 검증, 나라 이름으로 확인
        validateDuplicateNation(nationDto);

        //중복 나라가 없다면
        Nation nation = Nation.createNation(nationDto);
        nationRepository.save(nation);
        return nation;
    }

    private void validateDuplicateNation(NationDto nationDto) {
        List<Nation> findNations = nationRepository.findByName(nationDto.getName());
        for (Nation findNation : findNations) {
            System.out.println(findNation.getId() + " " + findNation.getName());
        }
        if (!findNations.isEmpty()) {
            throw new ApiRequestException("이미 존재하는 나라입니다.");
        }
    }

    //전체 나라 조회
    @Transactional(readOnly = true)
    public List<Nation> findNations() {
        return nationRepository.findAll();
    }

    //전체 나라 조회(클릭 횟수순으로)
    @Transactional(readOnly = true)
    public List<Nation> findNationsOrderByClicked() {
        return nationRepository.findAllOrderByClicked(Sort.by(Sort.Direction.DESC, "clicked"));
    }

    //아이디로 나라 조회
    @Transactional(readOnly = true)
    public Nation findOne(Long nationId) {
        return nationRepository.findOne(nationId);
    }

    //이름으로 나라 조회
    @Transactional(readOnly = true)
    public List<Nation> findByName(String name) {
        List<Nation> nations = nationRepository.findByName(name);
        if (nations.isEmpty()) {
            throw new ApiRequestException("해당 나라가 등록되어 있지 않습니다.");
        }
        return nations;
    }

    //나라 클릭 횟수 증가
    @Transactional
    public Nation addClicked(String nationName) {
        Nation nation = nationRepository.findByName(nationName).get(0);
        int clicked = nation.getClicked();
        nation.setClicked(++clicked);
        return nation;
    }
}
