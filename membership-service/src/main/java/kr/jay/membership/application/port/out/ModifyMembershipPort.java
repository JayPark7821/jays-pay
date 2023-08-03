package kr.jay.membership.application.port.out;

import kr.jay.membership.adapter.out.persistence.MembershipJpaEntity;
import kr.jay.membership.domain.Membership;

/**
 * ModifyMembershipPort
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/03
 */
public interface ModifyMembershipPort {
	MembershipJpaEntity modifyMembership(
		Membership.MembershipId membershipId
		,Membership.MembershipName membershipName
		, Membership.MembershipEmail membershipEmail
		, Membership.MembershipAddress membershipAddress
		, Membership.MembershipIsValid membershipIsValid
		,Membership.MembershipIsCorp membershipIsCorp
	);
}
