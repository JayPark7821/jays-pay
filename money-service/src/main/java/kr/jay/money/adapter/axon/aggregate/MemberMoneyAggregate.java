package kr.jay.money.adapter.axon.aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.*;

import java.util.UUID;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import kr.jay.money.adapter.axon.command.MemberMoneyCreatedCommand;
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
		log.info("MemberMoneyCreatedCommand handler");

		apply(new MemberMoneyCreatedEvent(command.getMembershipId()));
	}

	@EventSourcingHandler
	public void on(MemberMoneyCreatedEvent event) {
		log.info("MemberMoneyCreatedEvent handler");
		this.id = UUID.randomUUID().toString();
		this.membershipId = Long.parseLong(event.getMembershipId());
		this.balance = 0;
	}
}
