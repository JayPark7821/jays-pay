package kr.jay.common.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CheckRegisteredBankAccountCommand
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/09/08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckRegisteredBankAccountCommand {

	@TargetAggregateIdentifier
	private String aggregateIdentifier;

	private String rechargeRequestId;

	private String membershipId;




}
