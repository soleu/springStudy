package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em; // JPA에서의 동작 관리

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);//insert쿼리를 만들어서 member에 넣어주고, id값까지 생성해준다.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member=  em.find(Member.class,id); //select문 대신 JPA로 조회하는 방법
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result =em.createQuery("select m from Member m where m.name = :name",Member.class)
                .setParameter("name",name)
                .getResultList();
        return result.stream().findAny();
        //findAll에서와 같은 방식으로 코드를 짜지 못하는 이유는 찾는 값의 타입(List)과 결과 값의 타입(Member)가 다르기 때문
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member as m",Member.class)
                .getResultList();
        //member entity 자체를 select(결국 *과 같음)
    }
}
