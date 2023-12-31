package kr.jay.banking.application.service;

import kr.jay.banking.adapter.axon.command.CreateFirmBankingRequestCommand;
import kr.jay.banking.adapter.axon.command.UpdateFirmBankingRequestCommand;
import kr.jay.banking.adapter.out.external.bank.ExternalFirmbankingRequest;
import kr.jay.banking.adapter.out.external.bank.FirmbankingResult;
import kr.jay.banking.adapter.out.persistence.FirmbankingRequestJpaEntity;
import kr.jay.banking.adapter.out.persistence.FirmbankingRequestMapper;
import kr.jay.banking.application.port.in.RequestFirmbankingCommand;
import kr.jay.banking.application.port.in.RequestFirmbankingUseCase;
import kr.jay.banking.application.port.in.UpdateFirmBankingCommand;
import kr.jay.banking.application.port.in.UpdateFirmBankingUseCase;
import kr.jay.banking.application.port.out.RequestExternalFirmbankingPort;
import kr.jay.banking.application.port.out.RequestFirmbankingPort;
import kr.jay.banking.domain.FirmbankingRequest;
import kr.jay.common.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;
import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;


@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional
public class RequestFirmbankingService implements RequestFirmbankingUseCase, UpdateFirmBankingUseCase {
    private final FirmbankingRequestMapper mapper;
    private final RequestFirmbankingPort requestFirmbankingPort;
    private final RequestExternalFirmbankingPort requestExternalFirmbankingPort;
    private final CommandGateway commandGateway;

    @Override
    public FirmbankingRequest requestFirmbanking(RequestFirmbankingCommand command) {

        // Business Logic
        // a -> b 계좌

        // 1. 요청에 대해 정보를 먼저 write . "요청" 상태로
        FirmbankingRequestJpaEntity requestedEntity = requestFirmbankingPort.createFirmbankingRequest(
                new FirmbankingRequest.FromBankName(command.getFromBankName()),
                new FirmbankingRequest.FromBankAccountNumber(command.getFromBankAccountNumber()),
                new FirmbankingRequest.ToBankName(command.getToBankName()),
                new FirmbankingRequest.ToBankAccountNumber(command.getToBankAccountNumber()),
                new FirmbankingRequest.MoneyAmount(command.getMoneyAmount()),
                new FirmbankingRequest.FirmbankingStatus(0),
                new FirmbankingRequest.AggregateIdentifier("")
        );

        // 2. 외부 은행에 펌뱅킹 요청
        FirmbankingResult result = requestExternalFirmbankingPort.requestExternalFirmbanking(new ExternalFirmbankingRequest(
                command.getFromBankName(),
                command.getFromBankAccountNumber(),
                command.getToBankName(),
                command.getToBankAccountNumber()
        ));

        // Transactional UUID
        UUID randomUUID = UUID.randomUUID();
        requestedEntity.setUuid(randomUUID.toString());

        // 3. 결과에 따라서 1번에서 작성했던 FirmbankingRequest 정보를 Update
        if (result.getResultCode() == 0){
            // 성공
            requestedEntity.setFirmbankingStatus(1);
        } else {
            // 실패
            requestedEntity.setFirmbankingStatus(2);
        }

        // 4. 결과를 리턴하기 전에 바뀐 상태 값을 기준으로 다시 save
        return mapper.mapToDomainEntity(requestFirmbankingPort.modifyFirmbankingRequest(requestedEntity), randomUUID);
    }

    @Override
    public void requestFirmbankingByEvent(final RequestFirmbankingCommand command) {
        final CreateFirmBankingRequestCommand requestCommand = CreateFirmBankingRequestCommand.builder()
            .formBankName(command.getFromBankName())
            .fromBankAccountNumber(command.getFromBankAccountNumber())
            .toBankName(command.getToBankName())
            .toBankAccountNumber(command.getToBankAccountNumber())
            .moneyAmount(command.getMoneyAmount())
            .build();

        commandGateway.send(requestCommand)
            .whenComplete(
                (result, throwable) -> {
                    if (throwable != null) {
                        log.error("error", throwable);
                        throw new RuntimeException(throwable);
                    }else{
                        log.info("success {} ", result);

                        FirmbankingRequestJpaEntity requestedEntity = requestFirmbankingPort.createFirmbankingRequest(
                            new FirmbankingRequest.FromBankName(command.getFromBankName()),
                            new FirmbankingRequest.FromBankAccountNumber(command.getFromBankAccountNumber()),
                            new FirmbankingRequest.ToBankName(command.getToBankName()),
                            new FirmbankingRequest.ToBankAccountNumber(command.getToBankAccountNumber()),
                            new FirmbankingRequest.MoneyAmount(command.getMoneyAmount()),
                            new FirmbankingRequest.FirmbankingStatus(0),
                            new FirmbankingRequest.AggregateIdentifier(result.toString())
                        );
                        // 2. 외부 은행에 펌뱅킹 요청
                        FirmbankingResult firmbankingResult = requestExternalFirmbankingPort.requestExternalFirmbanking(new ExternalFirmbankingRequest(
                            command.getFromBankName(),
                            command.getFromBankAccountNumber(),
                            command.getToBankName(),
                            command.getToBankAccountNumber()
                        ));

                        // 3. 결과에 따라서 1번에서 작성했던 FirmbankingRequest 정보를 Update
                        if (firmbankingResult.getResultCode() == 0){
                            // 성공
                            requestedEntity.setFirmbankingStatus(1);
                        } else {
                            // 실패
                            requestedEntity.setFirmbankingStatus(2);
                        }

                        requestFirmbankingPort.modifyFirmbankingRequest(requestedEntity);
                    }
                }
            );
        // command -> event sourcing
    }

    @Override
    public void updateFirmBanking(final UpdateFirmBankingCommand command) {
        final UpdateFirmBankingRequestCommand requestCommand =
            new UpdateFirmBankingRequestCommand(command.getFirmBankingAggregateIdentifier(), command.getStatus());

        commandGateway.send(requestCommand)
            .whenComplete(
                (result, throwable) -> {
                    if (throwable != null) {
                        log.error("error", throwable);
                        throw new RuntimeException(throwable);
                    } else {
                        log.info("success {} ", result);
                        final FirmbankingRequestJpaEntity firmBankingRequestEntity = requestFirmbankingPort.getFirmBankingRequest(
                            new FirmbankingRequest.AggregateIdentifier(command.getFirmBankingAggregateIdentifier())
                        );

                        // status의 변경으로 인한 외부 은행과의 커뮤니케이션

                        firmBankingRequestEntity.setFirmbankingStatus(command.getStatus());
                        requestFirmbankingPort.modifyFirmbankingRequest(firmBankingRequestEntity);

                    }
                });

    }
}
