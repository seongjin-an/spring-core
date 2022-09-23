package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

public class SingletonWithPrototypeTest1 {
    @DisplayName("FIND PROTOTYPE")
    @Test
    void findPrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addAccount();
        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addAccount();
        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @DisplayName("싱글톤 클라이언트 -> 프로토타입")
    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        Assertions.assertThat(count2).isEqualTo(2);
    }

    //기대한대로 되기는 하지만 추천하지 않는 방법
    @DisplayName("applicationContext 빈조회(DL) && 싱글톤 클라이언트 -> 프로토타입")
    @Test
    void singletonClientUsePrototype2() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBean2.class, PrototypeBean.class);
        ClientBean2 clientBean1 = ac.getBean(ClientBean2.class);
        int count1 = clientBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);

        ClientBean2 clientBean2 = ac.getBean(ClientBean2.class);
        int count2 = clientBean2.logic();
        Assertions.assertThat(count2).isEqualTo(1);
    }

    //추천
    @DisplayName("ObjectProvider && 싱글톤 클라이언트 -> 프로토타입")
    @Test
    void singletonClientUsePrototype3() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBean3.class, PrototypeBean.class);
        ClientBean3 clientBean1 = ac.getBean(ClientBean3.class);
        int count1 = clientBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);

        ClientBean3 clientBean2 = ac.getBean(ClientBean3.class);
        int count2 = clientBean2.logic();
        Assertions.assertThat(count2).isEqualTo(1);
    }

    //추천
    @DisplayName("javax inject && 싱글톤 클라이언트 -> 프로토타입")
    @Test
    void singletonClientUsePrototype4() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBean4.class, PrototypeBean.class);
        ClientBean4 clientBean1 = ac.getBean(ClientBean4.class);
        int count1 = clientBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);

        ClientBean4 clientBean2 = ac.getBean(ClientBean4.class);
        int count2 = clientBean2.logic();
        Assertions.assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBean {
        private final PrototypeBean prototypeBean; // 생성 시점에 주입
        @Autowired
        public ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }

        public int logic() {
            prototypeBean.addAccount();
            return prototypeBean.getCount();
        }
    }

    @Scope("singleton")
    static class ClientBean2 {
        @Autowired private ApplicationContext ac;

        public int logic() {
            PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class);
            prototypeBean.addAccount();
            return prototypeBean.getCount();
        }
    }

    @Scope("singleton")
    static class ClientBean3 {
        @Autowired
//        private ObjectFactory<PrototypeBean> prototypeBeanProvider;
        private ObjectProvider<PrototypeBean> prototypeBeanProvider;

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();//찾아준다.
            prototypeBean.addAccount();
            return prototypeBean.getCount();
        }
    }

    @Scope("singleton")
    static class ClientBean4 {
        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.get();//찾아준다.
            prototypeBean.addAccount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addAccount() {
            count++;
        }
        public int getCount() {
            return count;
        }
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }
        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
