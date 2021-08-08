package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository=new MemoryMemberRepository();

    @AfterEach //각 메서드가 끝날때마다 동작하도록 만듬
    public void afterEach(){
        repository.clearStore();
    }

    @Test //save 메서드 실행
    public void save(){
        Member member =new Member();
        member.setName("spring");

        repository.save(member);

        Member result=repository.findById(member.getId()).get();//optional 에서 꺼낼때는 .get()
        Assertions.assertEquals(member,result);//두개가 동일한지 확인
        //assertThat(member).isEqualTo(result);//위와 동일한 방법이나, 요즘 쓰는 방식
    }
    @Test
    public void findByName(){
        //member 2명 가입
        Member member1= new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2= new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result= repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }
    @Test
    public void findAll(){
        Member member1=new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2=new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result= repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
        }
