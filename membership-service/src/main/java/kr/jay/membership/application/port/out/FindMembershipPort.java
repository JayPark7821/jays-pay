package kr.jay.membership.application.port.out;

import kr.jay.membership.adapter.out.persistence.MembershipJpaEntity;
import kr.jay.membership.domain.Membership;
/**
 * FindMembershipPort
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/02
 */
public interface FindMembershipPort {
    MembershipJpaEntity findMembership(
            Membership.MembershipId membershipId
    );
}