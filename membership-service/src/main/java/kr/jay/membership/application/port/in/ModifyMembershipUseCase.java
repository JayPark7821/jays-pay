package kr.jay.membership.application.port.in;

import kr.jay.membership.domain.Membership;

/**
 * ModifyMembershipUseCase
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/03
 */
public interface ModifyMembershipUseCase {

	Membership modifyMembership(ModifyMembershipCommand command);
}
