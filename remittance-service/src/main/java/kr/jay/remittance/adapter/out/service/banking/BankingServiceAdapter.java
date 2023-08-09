package kr.jay.remittance.adapter.out.service.banking;

import org.springframework.beans.factory.annotation.Value;

import kr.jay.common.CommonHttpClient;
import kr.jay.common.ExternalSystemAdapter;
import kr.jay.remittance.application.port.out.banking.BankingInfo;
import kr.jay.remittance.application.port.out.banking.BankingPort;
import lombok.RequiredArgsConstructor;

/**
 * BankingServiceAdapter
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/08
 */
@ExternalSystemAdapter
@RequiredArgsConstructor
public class BankingServiceAdapter implements BankingPort {


	private final CommonHttpClient bankingServiceHttpClient;

	@Value("${service.banking.url}")
	private String bankingServiceEndpoint;

	@Override
	public BankingInfo getMembershipBankingInfo(final String bankName, final String bankAccountNumber) {
		return null;
	}

	@Override
	public boolean requestFirmbanking(final String bankName, final String bankAccountNumber, final int amount) {
		return false;
	}
}
