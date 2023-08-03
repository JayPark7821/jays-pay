package kr.jay.membership.application.port.in;

import kr.jay.membership.domain.Membership;

/**
 * RegisterMembershipUseCase
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/02
 */

public interface RegisterMembershipUseCase {

	Membership registerMembership(RegisterMembershipCommand command);

}
