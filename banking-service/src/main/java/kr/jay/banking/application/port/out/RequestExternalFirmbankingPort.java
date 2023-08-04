package kr.jay.banking.application.port.out;

import kr.jay.banking.adapter.out.external.bank.ExternalFirmbankingRequest;
import kr.jay.banking.adapter.out.external.bank.FirmbankingResult;

public interface RequestExternalFirmbankingPort {
    FirmbankingResult requestExternalFirmbanking(ExternalFirmbankingRequest request);
}
