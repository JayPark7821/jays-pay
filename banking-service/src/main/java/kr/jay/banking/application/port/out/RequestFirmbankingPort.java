package kr.jay.banking.application.port.out;

import kr.jay.banking.adapter.out.persistence.FirmbankingRequestJpaEntity;
import kr.jay.banking.domain.FirmbankingRequest;

public interface RequestFirmbankingPort {

    FirmbankingRequestJpaEntity createFirmbankingRequest(
            FirmbankingRequest.FromBankName fromBankName,
            FirmbankingRequest.FromBankAccountNumber fromBankAccountNumber,
            FirmbankingRequest.ToBankName toBankName,
            FirmbankingRequest.ToBankAccountNumber toBankAccountNumber,
            FirmbankingRequest.MoneyAmount moneyAmount,
            FirmbankingRequest.FirmbankingStatus firmbankingStatus,
            FirmbankingRequest.AggregateIdentifier aggregateIdentifier
    );

    FirmbankingRequestJpaEntity modifyFirmbankingRequest(
            FirmbankingRequestJpaEntity entity
    );

    FirmbankingRequestJpaEntity getFirmBankingRequest(
        FirmbankingRequest.AggregateIdentifier aggregateIdentifier
    );
}
