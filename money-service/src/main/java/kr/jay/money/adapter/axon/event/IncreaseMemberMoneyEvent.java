package kr.jay.money.adapter.axon.event;

import javax.validation.constraints.NotNull;

import kr.jay.common.SelfValidating;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * IncreaseMemberMoneyEvent
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/09/05
 */


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class IncreaseMemberMoneyEvent extends SelfValidating<MemberMoneyCreatedEvent> {

	private String aggregateIdentifier;
	private String targetMembershipId;
	private int amount;
}