package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    //private final MemberService memberService=new MemberService(); //memberController말고 다른 컨트롤러들이 memberSerivice를 가져다 쓸수있게됨
    //여러개 생성할 필요가 없는 친구들은 딱 하나만 생길 수 있도록 관리함

    private final MemberService memberService;

    @Autowired //생성자가 autuwired일때 spring이 MemberService를 가져와서 Controller에 연결을 시켜줌.
    public MemberController(MemberService memberService){
        this.memberService=memberService;
    }
}
