package com.example.triptobuy.travel.controller;

import com.example.triptobuy.travel.domain.Nation;
import com.example.triptobuy.travel.dto.NationDto;
import com.example.triptobuy.travel.service.NationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NationController {

    private final NationService nationService;

    /**
     * @return 나라 전체 리스트
     */
    @GetMapping("/nationList")
    public List<Nation> getNationList() {
        return nationService.findNations();
    }

    /**
     * @return 나라 전체 리스트 (클릭 횟수 높은 순으로)
     */
    @GetMapping("/nationList/recommend")
    public List<Nation> getNationListOrderByClicked() {
        return nationService.findNationsOrderByClicked();
    }

    /**
     * @param nationName
     * @return nationName 해당 나라 정보
     */
    @GetMapping("/nations")
    public Nation getNation(@RequestParam String nationName) {
        return nationService.findByName(nationName).get(0);
    }

    /**
     * @param nationDto
     * @return nationDto 에 대한 나라 추가
     */
    @PostMapping("/nations")
    public Nation postNation(@RequestBody NationDto nationDto) {
        return nationService.join(nationDto);
    }

    /**
     * @param nationName
     * @return nationName 에 대한 클릭 수 증가
     */
    @PatchMapping("/clicked/nations")
    public void addNationClicked(@RequestParam String nationName) {
        nationService.addClicked(nationName);
    }

}
