package hello.itemservice.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MessageSourceTest {
    @Autowired
    MessageSource ms;

    @DisplayName("message 기본")
    @Test
    public void helloMessage() throws Exception{
        String result = ms.getMessage("hello", null, null);
        assertThat(result).isEqualTo("안녕");
    }

    @DisplayName("notFoundMessageCode 에러")
    @Test
    public void notFoundMessageCode() throws Exception{
//        ms.getMessage("no_code", null, null);
        assertThatThrownBy(() -> ms.getMessage("no_code", null, null))
                .isInstanceOf(NoSuchMessageException.class);
    }

    @DisplayName("기본 메시지")
    @Test
    public void notFoundMessageCodeDefaultMessage() throws Exception{
        String result = ms.getMessage("no_code", null, "기본 메시지", null);
        assertThat(result).isEqualTo("기본 메시지");
    }

    @DisplayName("인자값 사용")
    @Test
    public void argumentMessage() throws Exception{
        String message = ms.getMessage("hello.name", new Object[]{"Spring!"}, null);
        assertThat(message).isEqualTo("안녕 Spring!");
    }

    @DisplayName("defaultLang")
    @Test
    public void defaultLang() throws Exception{
        assertThat(ms.getMessage("hello", null, null)).isEqualTo("안녕");
        assertThat(ms.getMessage("hello", null, Locale.KOREA)).isEqualTo("안녕");
    }

    @DisplayName("enLang")
    @Test
    public void enLang() throws Exception{
        assertThat(ms.getMessage("hello", null, Locale.ENGLISH)).isEqualTo("hello");
    }
}
