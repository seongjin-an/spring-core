package hello.springtx.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class StreamTest {

    @DisplayName("REDUCE TEST")
    @Test
    public void reduceTest() throws Exception{
        Person p1 = new Person("ansj1", 1);
        Person p2 = new Person("ansj2", 2);
        Person p3 = new Person("ansj3", 3);

        List<Person> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);

        Person person = list.stream().reduce((person1, person2) -> {
            String p1Name = person1.getName();
            int p1Age = person1.getAge();
            String p2Name = person2.getName();
            int p2Age = person2.getAge();

            return new Person(p1Name + p2Name, p1Age + p2Age);
        }).orElse(null);
        log.info("person: {}", person);
    }

    @Slf4j
    @Data
//    @AllArgsConstructor
    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            log.info("person constructed..");
            this.name = name;
            this.age = age;
        }
    }
}
