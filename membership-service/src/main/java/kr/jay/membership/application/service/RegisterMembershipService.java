package kr.jay.membership.application.service;

import org.springframework.transaction.annotation.Transactional;

import kr.jay.common.UseCase;
import kr.jay.membership.adapter.out.persistence.MembershipJpaEntity;
import kr.jay.membership.adapter.out.persistence.MembershipMapper;
import kr.jay.membership.application.port.in.RegisterMembershipCommand;
import kr.jay.membership.application.port.in.RegisterMembershipUseCase;
import kr.jay.membership.application.port.out.RegisterMembershipPort;
import kr.jay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;

/**
 * RegisterMembershipService
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/02
 */
@UseCase
@Transactional
@RequiredArgsConstructor
public class RegisterMembershipService implements RegisterMembershipUseCase {

	private final RegisterMembershipPort registerMembershipPort;
	private final MembershipMapper membershipMapper;
	@Override
	public Membership registerMembership(final RegisterMembershipCommand command) {
		// TODO Auto-generated method stub
		final MembershipJpaEntity membership = registerMembershipPort.createMembership(
			new Membership.MembershipName(command.getName()),
			new Membership.MembershipEmail(command.getEmail()),
			new Membership.MembershipAddress(command.getAddress()),
			new Membership.MembershipIsValid(command.isValid()),
			new Membership.MembershipIsCorp(command.isCorp())
		);
		return membershipMapper.mapToDomainEntity(membership);
	}
}
