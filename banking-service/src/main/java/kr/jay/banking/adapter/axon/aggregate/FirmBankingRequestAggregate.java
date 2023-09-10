package kr.jay.banking.adapter.axon.aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.*;

import java.util.UUID;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import kr.jay.banking.adapter.axon.command.CreateFirmBankingRequestCommand;
import kr.jay.banking.adapter.axon.command.UpdateFirmBankingRequestCommand;
import kr.jay.banking.adapter.axon.event.FirmBankingRequestCreatedEvent;
import kr.jay.banking.adapter.axon.event.FirmBankingRequestUpdatedEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * FirmBankingRequestAggregate
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/09/07
 */

@Slf4j
@Aggregate()
@Data
@NoArgsConstructor
public class FirmBankingRequestAggregate {

	@AggregateIdentifier
	private String id;

	private String formBankName;
	private String fromBankAccountNumber;
	private String toBankName;
	private String toBankAccountNumber;
	private int moneyAmount;
	private int firmBankingStatus;

	@CommandHandler
	public FirmBankingRequestAggregate(final CreateFirmBankingRequestCommand command){
		log.info("CreateFirmBankingRequestCommand handler");

		apply(new FirmBankingRequestCreatedEvent(
			command.getFormBankName(),
			command.getFromBankAccountNumber(),
			command.getToBankName(),
			command.getToBankAccountNumber(),
			command.getMoneyAmount())
		);
	}

	@CommandHandler
	public String handle(UpdateFirmBankingRequestCommand command){
		log.info("UpdateFirmBankingRequestCommand handler");

		final String id = command.getAggregateIdentifier();
		apply(new FirmBankingRequestUpdatedEvent(command.getFirmBankingStatus()));

		return id;
	}

	@EventSourcingHandler
	public void on(final FirmBankingRequestCreatedEvent event) {
		log.info("FirmBankingRequestCreatedEvent handler");

		id = UUID.randomUUID().toString();
		formBankName = event.getFormBankName();
		fromBankAccountNumber = event.getFromBankAccountNumber();
		toBankName = event.getToBankName();
		toBankAccountNumber = event.getToBankAccountNumber();
	}

	@EventSourcingHandler
	public void on(final FirmBankingRequestUpdatedEvent event) {
		log.info("FirmBankingRequestUpdatedEvent handler");

		this.firmBankingStatus = event.getFirmBankingStatus();
	}

}
