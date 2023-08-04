package kr.jay.banking.application.port.in;


import kr.jay.banking.domain.FirmbankingRequest;

public interface RequestFirmbankingUseCase {
    FirmbankingRequest requestFirmbanking(RequestFirmbankingCommand command);
}
