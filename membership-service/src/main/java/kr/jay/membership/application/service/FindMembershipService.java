
package kr.jay.membership.application.service;

import javax.transaction.Transactional;

import kr.jay.common.UseCase;
import kr.jay.membership.adapter.out.persistence.MembershipJpaEntity;
import kr.jay.membership.adapter.out.persistence.MembershipMapper;
import kr.jay.membership.application.port.in.FindMembershipCommand;
import kr.jay.membership.application.port.in.FindMembershipUseCase;
import kr.jay.membership.application.port.out.FindMembershipPort;
import kr.jay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;

/**
 * FindMembershipService
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/02
 */

@UseCase
@RequiredArgsConstructor
@Transactional
public class FindMembershipService implements FindMembershipUseCase {

    private final FindMembershipPort findMembershipPort;

    private final MembershipMapper membershipMapper;
    @Override
    public Membership findMembership(FindMembershipCommand command) {
        MembershipJpaEntity entity = findMembershipPort.findMembership(new Membership.MembershipId(command.getMembershipId()));
        return membershipMapper.mapToDomainEntity(entity);
    }
}