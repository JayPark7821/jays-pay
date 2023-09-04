package kr.jay.money.application.port.in;

import javax.validation.constraints.NotNull;

import kr.jay.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * CreateMemberMoneyCommand
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/09/04
 */
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class CreateMemberMoneyCommand extends SelfValidating<IncreaseMoneyRequestCommand> {

	@NotNull
	private final String membershipId;

	public CreateMemberMoneyCommand(@NotNull final String membershipId) {
		this.membershipId = membershipId;
		this.validateSelf();
	}
}
