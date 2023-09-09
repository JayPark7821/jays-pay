package kr.jay.money.adapter.axon.saga;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import kr.jay.common.event.CheckRegisteredBankAccountCommand;
import kr.jay.money.adapter.axon.saga.event.RechargingRequestCreatedEvent;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * MoneyRechargeSaga
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/09/08
 */

@Slf4j
@Saga
@NoArgsConstructor
public class MoneyRechargeSaga {

	private transient CommandGateway commandGateway;

	@Autowired
	public void setCommandGateway(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}

	@StartSaga
	@SagaEventHandler(associationProperty = "rechargingRequestId")
	public void handle(RechargingRequestCreatedEvent event) {
		log.info("RechargingRequestCreatedEvent saga start");
		final String rechargingRequestId = event.getRechargingRequestId();

		SagaLifecycle.associateWith("rechargingRequestId", rechargingRequestId);

		// 충전 요청 시작

		// 뱅킹 계좌 등록 여부 확인
		// CheckRegisteredBankAccountCommand -> Check bank account
		// axon server -> banking service -> common
		commandGateway.send(
			new CheckRegisteredBankAccountCommand(
				"",
				rechargingRequestId,
				event.getMembershipId()
			)
		);

	}
}
