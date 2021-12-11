package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class Memberservice {

    private final MemberRepository memberRepository;

    public Memberservice(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    
    
    /**
     * 회원 가입
     * @param member
     * @return
     */
    public Long join(Member member){
        //같은 이름이 있는 중복 회원 x
        //Null일 가능성이 있으면 Optional 로 한번 감싸주어 반환을 해준다.
        validateDuplicateMember(member); //중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
