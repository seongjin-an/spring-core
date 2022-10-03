package hello.itemservice.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.StringJoiner;

public class StringJoinerTest {
    @DisplayName("StringJoiner TEST")
    @Test
    public void stringJoinerTest() throws Exception{
        StringJoiner stringJoiner = new StringJoiner(",");
        String[] strs = new String[]{"one", "two", "three"};
        for (String str : strs) {
            stringJoiner.add(str);
        }
        System.out.println("stringJoiner.toString(): " + stringJoiner.toString());
    }
}
