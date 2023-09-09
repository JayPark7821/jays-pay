package kr.jay.money.adapter.axon.command;

import javax.validation.constraints.NotNull;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import kr.jay.common.SelfValidating;
import kr.jay.money.application.port.in.IncreaseMoneyRequestCommand;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * IncreaseMemberMoneyCommand
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/09/05
 */

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class IncreaseMemberMoneyCommand extends SelfValidating<IncreaseMoneyRequestCommand>  {


	@NotNull
	@TargetAggregateIdentifier
	private String aggregateIdentifier;
	@NotNull
	private final String membershipId;
	@NotNull
	private final int amount;
}