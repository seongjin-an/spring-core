package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryV0Test {

    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @DisplayName("CRUD TEST")
    @Test
    public void crudTest() throws Exception{
        Member member = new Member("memberV1", 10000);
        repository.save(member);
    }
}