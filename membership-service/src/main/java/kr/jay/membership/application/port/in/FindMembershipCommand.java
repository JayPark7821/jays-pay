package kr.jay.membership.application.port.in;

import kr.jay.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * FindMembershipCommand
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/02
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class FindMembershipCommand extends SelfValidating<FindMembershipCommand> {
	private final String membershipId;
	
}
