package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class MemberService {
    //memberRepository를 생성과 동시에 내부에서 선언해줄 수 있도록 함
    private  final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
    }

    /**
     *
     * 회원가입
     */
    public  Long join(Member member){
        //같은 이름이 있는 중복 회원은 X
        //Optional<Member> result=memberRepository.findByName(member.getName());
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName((member.getName()))
                        .ifPresent(m->{
                            throw new IllegalStateException(("이미 존재하는 회원입니다."));
                        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
