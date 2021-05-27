package com.bookstore.shop.service;

import com.bookstore.shop.domain.Member;
import com.bookstore.shop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("test1");

        //when
        Long id = memberService.join(member);

        //then
        assertEquals(member, memberRepository.find_id(id));

    }

    @Test(expected = IllegalStateException.class)
    public void 중복체크() throws Exception{

        Member member = new Member();
        member.setName("test");
        Member member1 = new Member();
        member1.setName("test");

        memberService.join(member);
        memberService.join(member1);

        fail("예외가 발생해야한다.");

    }

}