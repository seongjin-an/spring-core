package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Optional;

public class AutowiredTest {
    @DisplayName("autowired test")
    @Test
    public void autowiredTest() throws Exception{
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    @Component
    static class TestBean {
        //호출 안됨
        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            //의존 관계가 없다면, 수정자 메서드 자체가 호출이 안된다.
            System.out.println("noBean1 = " + noBean1);
        }
        //null 호출
        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            //수정자 메서드가 호출은 되지만 null 로 들어온다.
            System.out.println("noBean2 = " + noBean2);
        }
        //Optional.empty 호출
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            //수정자 메서드가 호출은 되지만 Optional.mepty 가 들어온다.
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
