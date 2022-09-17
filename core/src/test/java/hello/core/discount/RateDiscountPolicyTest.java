package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @DisplayName("VIP 는 10% 할인이 적용되어야 함")
    @Test
    public void vipTest() throws Exception{
        Member member = new Member(1L, "memberVIP", Grade.VIP);

        int discount = discountPolicy.discount(member, 10000);

        assertThat(discount).isEqualTo(1000);
    }

    @DisplayName("VIP 가 아니라면 할인이 적용되지 않아야 함")
    @Test
    public void notVipTest() throws Exception{
        Member memberBasic = new Member(2L, "memberBasic", Grade.BASIC);

        int discount = discountPolicy.discount(memberBasic, 10000);

        assertThat(discount).isEqualTo(0);
    }

}