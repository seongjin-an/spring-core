package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImpl2Test {
    @DisplayName("create order dummy test")
    @Test
    public void createOrder() throws Exception{
        OrderServiceImpl2 orderService = new OrderServiceImpl2();
//        orderService.createOrder(1L, "itemA", 10000); //NPTE

        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));
        OrderServiceImpl orderservice2 = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order order = orderservice2.createOrder(1L, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}