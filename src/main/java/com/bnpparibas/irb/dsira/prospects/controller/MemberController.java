package com.bnpparibas.irb.dsira.prospects.controller;

import com.bnpparibas.irb.dsira.prospects.model.Member;
import com.bnpparibas.irb.dsira.prospects.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping(value = "/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping
    public Flux<Member> getAll() {
        return memberRepository.findAll();
    }

    @GetMapping(value = "/{name}")
    public Mono<Member> getOne(@PathVariable String name) {
        return memberRepository.findByName(name);
    }

    @PostMapping
    public Mono<Member> createMember(@RequestBody Member member) {
        return memberRepository.save(member);
    }

    @PostMapping(value = "/{number}")
    public Flux<Member> createMembers(@PathVariable("number") int number) {
        return generateRandomMember(number).subscribeOn(Schedulers.boundedElastic());
    }

    private Flux<Member> generateRandomMember(int number) {
        return Mono.fromSupplier(
                () -> Member.builder().name(RandomStringUtils.randomAlphabetic(5)).build())
                .flatMap(memberRepository::save)
                .repeat(number);
    }

    @PutMapping
    public Mono<Member> updateMember(@RequestBody Member member) {
        return memberRepository
                .findByName(member.getName())
                .flatMap(memberResult -> memberRepository.save(member));
    }

    @DeleteMapping
    public Mono<Void> deleteMember(@RequestBody Member member) {
        return memberRepository.deleteById(member.getId());
    }
}
