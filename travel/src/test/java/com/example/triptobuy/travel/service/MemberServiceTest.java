//package com.example.triptobuy.travel.service;
//
//import com.example.triptobuy.travel.domain.Member;
//import com.example.triptobuy.travel.domain.SearchProduct;
//import com.example.triptobuy.travel.dto.MemberDto;
//import com.example.triptobuy.travel.exception.ApiRequestException;
//import com.example.triptobuy.travel.repository.MemberRepository;
//import com.example.triptobuy.travel.repository.SearchRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // test 순서 명시 가능 @Order
//@Transactional()
//// test 끝나면 rollback을 해줘서 db에 반영이 안되게 해준다.
//// 마치 AfterEach로 data들 clear 해준 역할. 지운다기 보단 아예 반영이 안됨
//// 연속으로 test해도 에러가 안뜸. 없으면 이전에 한 test에 영향을 받아 에러 발생
//class MemberServiceTest {
//
//    @Autowired
//    MemberService memberService;
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Autowired
//    SearchService searchService;
//
//    @Autowired
//    SearchRepository searchRepository;
//
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    public MemberDto createMemberDto(String name, String pw, String email, int age, char gender, char status) {
//        MemberDto memberDto = new MemberDto();
//        memberDto.setName(name);
//        memberDto.setPw(pw);
//        memberDto.setEmail(email);
//        memberDto.setAge(age);
//        memberDto.setGender(gender);
//        memberDto.setStatus(status);
//        return memberDto;
//    }
//
//    /**
//     * 1. 회원 가입
//     * - 입력 데이터가 충분하지 않은 경우 처리하기
//     * - 중복 회원 (같은 email) 인 경우 처리하기
//     * - 비밀번호 최소 길이 제한하기
//     * - 회원 정보 수정 확인
//     */
//    @Test
//    @DisplayName("회원가입")
//    @Order(1)
//    public void test1() throws Exception {
//        //given
//        MemberDto memberDto = createMemberDto("kim", "12345678910", "abcd@gmail.com", 26, 'm', 'o');
//        //when
//        Member member = memberService.join(memberDto);
//        //then
//        assertNotNull(member.getId());
//        assertEquals(member.getName(), memberDto.getName());
//        assertTrue(passwordEncoder.matches(memberDto.getPw(), member.getPw()));
//        assertEquals(member.getEmail(), memberDto.getEmail());
//        assertEquals(member.getAge(), memberDto.getAge());
//        assertEquals(member.getGender(), memberDto.getGender());
//    }
//
//    @Test
//    @DisplayName("입력데이터가 불충분한 경우")
//    @Order(2)
//    public void test2() throws Exception {
//        //given
//        MemberDto memberDto = new MemberDto();
//        //when
//        ApiRequestException e = assertThrows(ApiRequestException.class, () -> memberService.join(memberDto));
//        //then
//        assertEquals(e.getMessage(), "이름은 필수 입력 값입니다.");
//    }
//
//    @Test
//    @DisplayName("중복 이메일로 회원가입하는 경우")
//    @Order(3)
//    public void test3() throws Exception {
//        //given
//        MemberDto memberDto1 = createMemberDto("kim", "12345678910", "abcd@gmail.com", 26, 'm', 'o');
//        MemberDto memberDto2 = createMemberDto("choi", "12345678910", "abcd@gmail.com", 26, 'm', 'o');
//        //when
//        Member member = memberService.join(memberDto1);
//        //then
//        ApiRequestException e = assertThrows(ApiRequestException.class, () -> memberService.join(memberDto2));
//        assertEquals(e.getMessage(), "이미 존재하는 회원입니다.");
//    }
//
//    @Test
//    @DisplayName("비밀번호 길이가 짧은 경우")
//    @Order(4)
//    public void test4() throws Exception {
//        //given
//        MemberDto memberDto = createMemberDto("kim", "123", "abcd@gmail.com", 26, 'm', 'o');
//        //when
//        ApiRequestException e = assertThrows(ApiRequestException.class, () -> memberService.join(memberDto));
//        //then
//        assertEquals(e.getMessage(), "비밀번호는 최소 10자리입니다.");
//    }
//
//    @Test
//    @DisplayName("회원 정보 수정 확인")
//    @Order(5)
//    public void test5() throws Exception {
//        //given
//        MemberDto memberDto = createMemberDto("kim", "12345678910", "abcd@gmail.com", 26, 'm', 'o');
//        //when
//        Member member = memberService.join(memberDto);
//        member.setName("choi");
//        //then
//        Member findMember = memberService.findByEmail("abcd@gmail.com").get(0);
//        assertEquals(findMember.getName(), "choi");
//    }
//
//    /**
//     * 2. 회원 목록
//     */
//    @Test
//    @DisplayName("회원목록 확인(인원 수)")
//    @Order(6)
//    public void test6() throws Exception {
//        //given
//        MemberDto memberDto1 = createMemberDto("kim", "12345678910", "abcd@gmail.com", 26, 'm', 'o');
//        MemberDto memberDto2 = createMemberDto("choi", "12345678910", "efgh@gmail.com", 26, 'm', 'o');
//        //when
//        Member member1 = memberService.join(memberDto1);
//        Member member2 = memberService.join(memberDto2);
//        //then
////        assertEquals(memberService.findMembers().stream().count(), 2);
//    }
//
//    /**
//     *
//     * 3. 회원 검색 (회원 이름, email)
//     * - 회원 이름, email에 해당 회원이 없는 경우 처리하기
//     */
//    @Test
//    @DisplayName("이메일로 회원 검색")
//    @Order(7)
//    public void 회원검색_이메일() throws Exception {
//        //given
//        MemberDto memberDto = createMemberDto("kim", "12345678910", "abcd@gmail.com", 26, 'm', 'o');
//        //when
//        Member member = memberService.join(memberDto);
//        //then
//        Member findMember = memberService.findByEmail("abcd@gmail.com").get(0);
//        assertEquals(findMember, member);
//    }
//
//    @Test
//    @DisplayName("등록 안된 이메일로 회원 검색")
//    @Order(8)
//    public void test8() throws Exception {
//        //given
//        MemberDto memberDto = createMemberDto("kim", "12345678910", "abcd@gmail.com", 26, 'm', 'o');
//        //when
//        Member member = memberService.join(memberDto);
//        //then
//        ApiRequestException e = assertThrows(ApiRequestException.class, () -> memberService.findByEmail("xxx@gmail.com"));
//        assertEquals(e.getMessage(), "해당 회원이 존재하지 않습니다.");
//    }
//
//    /**
//     * 4. 회원 탈퇴 (회원의 검색 기록은 어떤 반응 -> 같이 삭제? 에러?)
//     *
//     */
//    @Test
//    @DisplayName("회원탈퇴")
//    @Order(9)
//    public void test9() throws Exception {
//        //given
//        MemberDto memberDto = createMemberDto("kim", "12345678910", "abcd@gmail.com", 26, 'm', 'o');
//        //when
//        Member member = memberService.join(memberDto);
//        memberService.deleteMember(member.getId());
//        //then
////        assertEquals(memberService.findMembers().stream().count(), 0);
//        ApiRequestException e = assertThrows(ApiRequestException.class, () -> memberService.findByEmail("abcd@gmail.com"));
//        assertEquals(e.getMessage(), "해당 회원이 존재하지 않습니다.");
//    }
//
//    /**
//     * 에러 발생
//     * @throws Exception
//     */
//    @Test
//    @DisplayName("회원 탈퇴 시, 검색기록 상태 확인")
//    @Order(10)
//    public void test10() throws Exception {
//        //given
//        MemberDto memberDto1 = createMemberDto("kim", "12345678910", "abcd@gmail.com", 26, 'm', 'o');
//        MemberDto memberDto2 = createMemberDto("choi", "12345678910", "efgh@gmail.com", 26, 'm', 'o');
//        //when
//        Member member1 = memberService.join(memberDto1);
//        Member member2 = memberService.join(memberDto2);
//        searchService.insertSearchProduct(member1.getId(), "장난감");
//        searchService.insertSearchProduct(member1.getId(), "화장품");
//        searchService.insertSearchProduct(member1.getId(), "노트북");
//        searchService.insertSearchProduct(member2.getId(), "장난감");
//        searchService.insertSearchProduct(member2.getId(), "장난감");
//        searchService.insertSearchProduct(member2.getId(), "장난감");
//        //then
//        memberService.deleteMember(member1.getId());
//        long size1 = searchService.findSearchProducts(member1.getId()).stream().count();
//        long size2 = searchService.findSearchProducts(member2.getId()).stream().count();
//        assertEquals(size1, 0);
//        assertEquals(size2, 1);
//    }
//
//    /**
//     * (상품 검색 5~7, 나라 검색 8~10, 도시 검색 11~13)
//     *
//     * 5. 회원이 상품 검색 (상품 등록 되있어야 함, 임의의 값으로 넣고 테스트)
//     * - 이전에 검색한 적이 있는 경우, 중복 삽입 X, 덮어쓰기(검색 날짜만 최신으로 변경 or 과거 기록 삭제 후 삽입)
//     */
//    @Test
//    @DisplayName("회원 상품 검색 확인, 중복 상품 검색 시 -> 이전 데이터 지우고 새로 등록")
//    @Order(11)
//    public void test11() throws Exception {
//        //given
//        MemberDto memberDto1 = createMemberDto("kim", "12345678910", "abcd@gmail.com", 26, 'm', 'o');
//        MemberDto memberDto2 = createMemberDto("choi", "12345678910", "efgh@gmail.com", 26, 'm', 'o');
//        //when
//        Member member1 = memberService.join(memberDto1);
//        Member member2 = memberService.join(memberDto2);
//        searchService.insertSearchProduct(member1.getId(), "장난감");
//        SearchProduct searchProduct1 = searchService.findSearchProducts(member1.getId()).get(0);
//        String firstSearchProductName = searchProduct1.getProductName();
//        LocalDateTime firstSearchTime = searchProduct1.getUPDATE_AT();
//
//        searchService.insertSearchProduct(member1.getId(), "화장품");
//        searchService.insertSearchProduct(member1.getId(), "노트북");
//
//        // "장난감"이라는 중복된 상품 검색
//        searchService.insertSearchProduct(member1.getId(), "장난감");
//        SearchProduct searchProduct2 = searchService.findSearchProducts(member1.getId()).get(2);
//        String secondSearchProductName = searchProduct2.getProductName();
//        LocalDateTime secondSearchTime = searchProduct2.getUPDATE_AT();
//
//
//        searchService.insertSearchProduct(member2.getId(), "장난감");
//        searchService.insertSearchProduct(member2.getId(), "화장품");
//        searchService.insertSearchProduct(member2.getId(), "노트북");
//        //then
//        int size = searchService.findSearchProducts(member1.getId()).size();
//        assertEquals(size, 3); // 중복 검색해도 총 검색 상품 개수는 동일 (최근 검색으로 갱신)
//        int size1 = searchRepository.findAll().size();
////        assertEquals(size1, 6);
//
//        assertEquals(firstSearchProductName, secondSearchProductName); // 검색 상품 이름은 같고
//        assertNotEquals(firstSearchTime, secondSearchTime); // 해당 시간은 바뀜 (최근 검색으로 갱신 되었으므로)
//    }
//
//    /**
//     * 6. 상품 검색 기록 삭제
//     */
//    @Test
//    @DisplayName("해당 상품 검색 기록 삭제")
//    @Order(12)
//    public void test12() throws Exception {
//        //given
//        MemberDto memberDto1 = createMemberDto("kim", "12345678910", "abcd@gmail.com", 26, 'm', 'o');
//        MemberDto memberDto2 = createMemberDto("choi", "12345678910", "efgh@gmail.com", 26, 'm', 'o');
//        //when
//        Member member1 = memberService.join(memberDto1);
//        Member member2 = memberService.join(memberDto2);
//        searchService.insertSearchProduct(member1.getId(), "장난감");
//        searchService.insertSearchProduct(member1.getId(), "화장품");
//        searchService.insertSearchProduct(member1.getId(), "노트북");
//        searchService.insertSearchProduct(member2.getId(), "장난감");
//        searchService.insertSearchProduct(member2.getId(), "화장품");
//        searchService.insertSearchProduct(member2.getId(), "노트북");
//        //then
//
//        List<SearchProduct> searchProducts = searchService.findSearchProducts(member1.getId());
//        for (SearchProduct searchProduct : searchProducts) {
//            if (searchProduct.getProductName() == "장난감") {
//                searchService.deleteSearchProduct(searchProduct.getId());
//                break;
//            }
//        }
//        int size1 = searchService.findSearchProducts(member1.getId()).size();
//        int size2 = searchService.findSearchProducts(member2.getId()).size();
//        assertEquals(size1, 2);
//        assertEquals(size2, 3);
//    }
//}