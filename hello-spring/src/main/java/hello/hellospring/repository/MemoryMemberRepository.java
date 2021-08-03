package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.swing.text.html.Option;
import java.util.*;

public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long,Member>store=new HashMap<>();
    private static long sequence =0L;//0,1,2..key값 생성

        @Override
        public Member save(Member member) {
            member.setId((++sequence));//id 생성
            store.put(member.getId(),member);//store에 저장
            return  member;
        }

        @Override
        public Optional<Member> findById(Long id) {
            //이런식으로 optional로 감싸면, 클라이언트에서 후처리가능
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public Optional<Member> findByName(String name) {
            return store.values().stream() //loop
                    .filter(member -> member.getName().equals(name)) //name 일치 확인
                    .findAny();//하나라도 찾으면 반환.없으면 null
        }

        @Override
        public List<Member> findAll() {
            return new ArrayList<>(store.values());
        }
    public void clearStore(){
        store.clear();
    }

}

