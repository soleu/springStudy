package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); //id로 반환
    Optional<Member> findByName(String name);//이름으로 반환
    List<Member> findAll();//전체 반환
}
