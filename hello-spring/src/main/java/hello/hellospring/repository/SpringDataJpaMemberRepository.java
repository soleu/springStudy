package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>,MemberRepository {
    //interface가 interface를 받을때는 extends 라고한다.
    //interface는 다중 상속이 가능하다

    @Override
    Optional<Member> findByName(String name);
}
