package kr.jay.money.adapter.axon.aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.*;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import kr.jay.money.adapter.axon.command.IncreaseMemberMoneyCommand;
import kr.jay.money.adapter.axon.command.MemberMoneyCreatedCommand;
import kr.jay.money.adapter.axon.event.IncreaseMemberMoneyEvent;
import kr.jay.money.adapter.axon.event.MemberMoneyCreatedEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * MemberMoneyAggregate
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/09/04
 */

@Slf4j
@Aggregate()
@Data
@NoArgsConstructor
public class MemberMoneyAggregate {

	@AggregateIdentifier
	private String id;

	private Long membershipId;

	private int balance;

	@CommandHandler
	public MemberMoneyAggregate(MemberMoneyCreatedCommand command) {
		System.out.println("MemberMoneyCreatedCommand Handler");

		apply(new MemberMoneyCreatedEvent(command.getMembershipId()));
	}

	@CommandHandler
	public String handle(@NotNull IncreaseMemberMoneyCommand command){
		System.out.println("IncreaseMemberMoneyCommand Handler");
		id = command.getAggregateIdentifier();

		// store event
		apply(new IncreaseMemberMoneyEvent(id, command.getMembershipId(), command.getAmount()));
		return id;
	}

	@EventSourcingHandler
	public void on(MemberMoneyCreatedEvent event) {
		System.out.println("MemberMoneyCreatedEvent Sourcing Handler");
		id = UUID.randomUUID().toString();
		membershipId = Long.parseLong(event.getMembershipId());
		balance = 0;
	}

	@EventSourcingHandler
	public void on(IncreaseMemberMoneyEvent event) {
		System.out.println("IncreaseMemberMoneyEvent Sourcing Handler");
		id = event.getAggregateIdentifier();
		membershipId = Long.parseLong(event.getTargetMembershipId());
		balance = event.getAmount();
	}

}
