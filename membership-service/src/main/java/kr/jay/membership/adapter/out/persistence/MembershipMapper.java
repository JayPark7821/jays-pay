package kr.jay.membership.adapter.out.persistence;

import org.springframework.stereotype.Component;

import kr.jay.membership.domain.Membership;

/**
 * MembershipMapper
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/02
 */
@Component
public class MembershipMapper {
	public Membership mapToDomainEntity(final MembershipJpaEntity membershipJpaEntity) {
		return Membership.generateMember(
			new Membership.MembershipId(membershipJpaEntity.getMemberId() + ""),
			new Membership.MembershipName(membershipJpaEntity.getName()),
			new Membership.MembershipEmail(membershipJpaEntity.getEmail()),
			new Membership.MembershipAddress(membershipJpaEntity.getAddress()),
			new Membership.MembershipIsValid(membershipJpaEntity.isValid()),
			new Membership.MembershipIsCorp(membershipJpaEntity.isCorp())
		);
	}
}
