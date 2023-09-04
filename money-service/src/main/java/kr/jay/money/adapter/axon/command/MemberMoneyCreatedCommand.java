package kr.jay.money.adapter.axon.command;

import javax.validation.constraints.NotNull;

import kr.jay.common.SelfValidating;
import kr.jay.money.application.port.in.IncreaseMoneyRequestCommand;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * CreateMemberMoneyCommand
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/09/04
 */

@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class MemberMoneyCreatedCommand extends SelfValidating<IncreaseMoneyRequestCommand> {

	@NotNull
	private String membershipId;

	public MemberMoneyCreatedCommand(@NotNull final String membershipId) {
		this.membershipId = membershipId;
		this.validateSelf();
	}
}
