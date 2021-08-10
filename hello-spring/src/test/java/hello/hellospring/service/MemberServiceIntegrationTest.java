package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {


//    @BeforeEach
//    public void beforeEach(){
//        memberRepository = new MemoryMemberRepository();
//        memberService = new MemberService(memberRepository);
//    }
    //직접 객체 생성 하지 않고

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository; //MemoryMemberRepository 아님

//    @AfterEach
//    public void afterEach(){memberRepository.clearStore();}

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
