package kr.jay.money.adapter.axon.event;

import javax.validation.constraints.NotNull;

import kr.jay.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * MemberMoneyCreatedEvent
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/09/04
 */

@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class MemberMoneyCreatedEvent extends SelfValidating<MemberMoneyCreatedEvent> {

	@NotNull
	private String membershipId;

	public MemberMoneyCreatedEvent(final String membershipId) {
		this.membershipId = membershipId;
		this.validateSelf();
	}
}
