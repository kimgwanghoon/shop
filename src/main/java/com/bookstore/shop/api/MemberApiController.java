package com.bookstore.shop.api;

import com.bookstore.shop.dto.MemberListDto;
import com.bookstore.shop.domain.Member;
import com.bookstore.shop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @GetMapping("/api/v2/members")
    public Result memberV2(){
        List<Member> findMembers = memberService.findMembers();
        List<MemberListDto> collect = findMembers.stream().map(m -> new MemberListDto(m.getId(),m.getName()))
                .collect(Collectors.toList());

        return new Result(collect.size(),collect);
    }

    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request){
        Member member = new Member();
        member.setName(request.getName());

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMEmberV2(@PathVariable("id") Long id, @RequestBody @Valid UpdateMemberRequset request){

        memberService.update(id, request.getName());
        Member findMember = memberService.findId(id);
        return new UpdateMemberResponse(findMember.getId(),findMember.getName());

    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private int count;
        private T data;
    }

    @Data
    static class UpdateMemberRequset{
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse{
        private Long id;
        private String name;
    }

    @Data
    static class CreateMemberRequest{
        private String name;
    }

    @Data
    static class CreateMemberResponse{
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }

}
