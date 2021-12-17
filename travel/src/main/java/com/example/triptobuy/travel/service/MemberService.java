package com.example.triptobuy.travel.service;

import com.example.triptobuy.travel.domain.Member;
import com.example.triptobuy.travel.dto.MemberDto;
import com.example.triptobuy.travel.exception.ApiRequestException;
import com.example.triptobuy.travel.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional // 트랜잭션, 영속성 컨텍스트
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    //회원가입
    @Transactional //디비 적용
    public Member join(MemberDto memberDto) {

        //중복 회원 검증, 중복 이메일 확인
        validateDuplicateMember(memberDto);

        //중복 회원 없다면
        Member member = Member.createMember(memberDto);
        String password = passwordEncoder.encode(member.getPw());
        member.setPw(password);
        memberRepository.save(member);
        return member;
    }
    private void validateDuplicateMember(MemberDto memberDto) {
        List<Member> findMembers = memberRepository.findByEmail(memberDto.getEmail());
        if (!findMembers.isEmpty()) {
            throw new ApiRequestException("이미 존재하는 회원입니다.");
        }
    }

    //전체 회원 조회
    @Transactional(readOnly = true) //읽기 전용, 성능 최적화
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //아이디로 회원 조회
    @Transactional(readOnly = true) //읽기 전용, 성능 최적화
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    //이름으로 회원 조회
    @Transactional(readOnly = true)
    public List<Member> findByName(String name) {
        return memberRepository.findByName(name);
    }

    //이메일로 회원 조회
    @Transactional(readOnly = true)
    public List<Member> findByEmail(String email) {

        //회원 존재 여부 확인
        validateNotExistMember(email);

        //회원이 존재한다면
        return memberRepository.findByEmail(email);
    }

    private void validateNotExistMember(String email) {
        List<Member> findMembers = memberRepository.findByEmail(email);
        if (findMembers.isEmpty()) {
            throw new ApiRequestException("해당 회원이 존재하지 않습니다.");
        }
    }

    //회원 탈퇴(아이디로 조회)
    @Transactional
    public void deleteMember(Long id) {
        memberRepository.deleteMember(id);
    }

    // 로그인 비밀번호 확인
    public void checkPw(String pw, String memberPw) {
        if (!passwordEncoder.matches(memberPw, pw)) {
            throw new ApiRequestException("비밀번호가 일치하지 않습니다");
        }
    }
}
