package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect //aop로 사용하는 방법
@Component //bean에 등록
public class TimeTraceAop {
    @Around("execution(* hello.hellospring..*(..))")
    //적용할 패키지경로
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start= System.currentTimeMillis();
        System.out.println("START"+joinPoint.toString());

        try{
            return joinPoint.proceed();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs=finish-start;
            System.out.println("END: "+joinPoint.toString()+" "+timeMs+"ms");

        }
    }
}
