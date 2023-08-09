package kr.jay.remittance.adapter.out.service.money;

import org.springframework.beans.factory.annotation.Value;

import kr.jay.common.CommonHttpClient;
import kr.jay.common.ExternalSystemAdapter;
import kr.jay.remittance.application.port.out.money.MoneyInfo;
import kr.jay.remittance.application.port.out.money.MoneyPort;
import lombok.RequiredArgsConstructor;

/**
 * MoneyServiceAdapter
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/08
 */
@RequiredArgsConstructor
@ExternalSystemAdapter
public class MoneyServiceAdapter implements MoneyPort {

	private final CommonHttpClient moneyServiceHttpClient;

	@Value("${service.money.url}")
	private String moneyServiceEndpoint;
	@Override
	public MoneyInfo getMoneyInfo(final String membershipId) {
		return null;
	}

	@Override
	public boolean requestMoneyRecharging(final String membershipId, final int amount) {
		return false;
	}

	@Override
	public boolean requestMoneyIncrease(final String membershipId, final int amount) {
		return false;
	}

	@Override
	public boolean requestMoneyDecrease(final String membershipId, final int amount) {
		return false;
	}
}
