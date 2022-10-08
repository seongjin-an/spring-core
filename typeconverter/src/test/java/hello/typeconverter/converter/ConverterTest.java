package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ConverterTest {
    @DisplayName("문자를 숫자로 변환하는 TEST")
    @Test
    public void stringToIntegerTest() throws Exception{
        StringToIntegerConverter converter = new StringToIntegerConverter();
        Integer result = converter.convert("10");
        assertThat(result).isEqualTo(10);
    }

    @DisplayName("숫자를 문자로 변환하는 TEST")
    @Test
    public void integerToString() throws Exception{
        IntegerToStringConverter converter = new IntegerToStringConverter();
        String result = converter.convert(10);
        assertThat(result).isEqualTo("10");
    }

    @DisplayName("문자를 IpPort 객체로 변환하는 TEST")
    @Test
    public void stringToIpPortTest() throws Exception{
        IpPortToStringConverter converter = new IpPortToStringConverter();
        IpPort source = new IpPort("127.0.0.1", 8080);
        String result = converter.convert(source);
        assertThat(result).isEqualTo("127.0.0.1:8080");
    }

    @DisplayName("IpPort 객체를 문자로 변환하는 TEST")
    @Test
    public void ipPortToStringTest() throws Exception{
        StringToIpPortConverter converter = new StringToIpPortConverter();
        String source = "127.0.0.1:8080";
        IpPort result = converter.convert(source);
        assertThat(result).isEqualTo(new IpPort("127.0.0.1", 8080));
    }
}