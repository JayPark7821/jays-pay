package kr.jay.banking.adapter.axon.saga;

import static org.axonframework.modelling.command.AggregateLifecycle.*;

import java.util.UUID;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * RegisteredBankAccountAggregate
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/09/08
 */

@Slf4j
@Aggregate
@Data
@NoArgsConstructor
public class RegisteredBankAccountAggregate {

	@AggregateIdentifier
	private String id;
	private String membershipId;
	private String bankName;
	private String bankAccountNumber;

	@CommandHandler
	public RegisteredBankAccountAggregate(CreateRegisteredBankAccountCommand command) {
		log.info("RegisteredBankAccountAggregate on CreateRegisteredBankAccountCommand");
		apply(
			new CreateRegisteredBankAccountEvent(
				command.getMembershipId(),
				command.getBankName(),
				command.getBankAccountNumber()
			)
		);
	}

	@EventSourcingHandler
	public void on(CreateRegisteredBankAccountEvent event) {
		log.info("RegisteredBankAccountAggregate on CreateRegisteredBankAccountEvent");
		this.id = UUID.randomUUID().toString();
		this.membershipId = event.getMembershipId();
		this.bankName = event.getBankName();
		this.bankAccountNumber = event.getBankAccountNumber();
	}
}
