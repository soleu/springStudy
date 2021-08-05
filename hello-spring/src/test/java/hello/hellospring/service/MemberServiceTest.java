package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.assertj.core.api.Assertions.*;

class MemberServiceTest {
    MemoryMemberRepository memberRepository;
    MemberService memberService;

    @BeforeEach
    //각 클래스를 실행하기 전에 각각의 멤버 서비스에 리포지토리를 넣어줌.(동일한 리포지토리 사용)
    //DI(Dependency Injection : 의존성 주입)
    public void beforeEach(){
        memberRepository=new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public  void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given : 뭔가가 주어졌는데
        Member member = new Member();
        member.setName("");
        //when : 이걸 실행했을 때
        Long saveId =memberService.join(member);

        //then : 이런 결과가 나와야한다.
        Member findMember=memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1=new Member();
        member1.setName("spring");

        Member member2=new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        //콤마 뒤를 실행할건데, 에러가 나면 에러코드를 출력해라
        IllegalStateException e = assertThrows(IllegalStateException.class,()-> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

}