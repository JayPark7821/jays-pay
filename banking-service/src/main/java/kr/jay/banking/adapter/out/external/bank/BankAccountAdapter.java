package kr.jay.banking.adapter.out.external.bank;

import org.springframework.stereotype.Component;

import kr.jay.banking.application.port.out.RequestBankAccountInfoPort;
import kr.jay.banking.application.port.out.RequestExternalFirmbankingPort;
import kr.jay.common.ExternalSystemAdapter;
import lombok.RequiredArgsConstructor;

/**
 * BankAccountAdapter
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/03
 */
@ExternalSystemAdapter
@RequiredArgsConstructor
public class BankAccountAdapter implements RequestBankAccountInfoPort, RequestExternalFirmbankingPort {

	@Override
	public BankAccount getBankAccountInfo(GetBankAccountRequest request) {

		// 실제로 외부 은행에 http 을 통해서
		// 실제 은행 계좌 정보를 가져오고

		// 실제 은행 계좌 -> BankAccount
		return new BankAccount(request.getBankName(), request.getBankAccountNumber(), true);
	}

	@Override
	public FirmbankingResult requestExternalFirmbanking(ExternalFirmbankingRequest request) {

		// 실제로 외부 은행에 http 통신을 통해서
		// 펌뱅킹 요청을 하고

		// 그 결과를
		// 외부 은행의 실제 결과를 -> 패캠 페이의 FirmbankingResult 파싱
		return new FirmbankingResult(0);
	}
}
