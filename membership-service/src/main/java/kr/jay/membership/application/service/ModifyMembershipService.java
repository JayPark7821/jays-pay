package kr.jay.membership.application.service;

import javax.transaction.Transactional;

import kr.jay.common.UseCase;
import kr.jay.membership.adapter.out.persistence.MembershipJpaEntity;
import kr.jay.membership.adapter.out.persistence.MembershipMapper;
import kr.jay.membership.application.port.in.ModifyMembershipCommand;
import kr.jay.membership.application.port.in.ModifyMembershipUseCase;
import kr.jay.membership.application.port.out.ModifyMembershipPort;
import kr.jay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;

/**
 * ModifyMembershipService
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/03
 */

@UseCase
@RequiredArgsConstructor
@Transactional
public class ModifyMembershipService implements ModifyMembershipUseCase {

	private final ModifyMembershipPort modifyMembershipPort;
	private final MembershipMapper membershipMapper;

	@Override
	public Membership modifyMembership(ModifyMembershipCommand command) {
		MembershipJpaEntity jpaEntity = modifyMembershipPort.modifyMembership(
			new Membership.MembershipId(command.getMembershipId()),
			new Membership.MembershipName(command.getName()),
			new Membership.MembershipEmail(command.getEmail()),
			new Membership.MembershipAddress(command.getAddress()),
			new Membership.MembershipIsValid(command.isValid()),
			new Membership.MembershipIsCorp(command.isCorp())
		);

		// entity -> Membership
		return membershipMapper.mapToDomainEntity(jpaEntity);
	}
}
