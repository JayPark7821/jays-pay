package kr.jay.money.application.port.out;

import kr.jay.common.RechargingMoneyTask;

/**
 * SendRechargingMoneyTaskPort
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/07
 */
public interface SendRechargingMoneyTaskPort {
	void sendRechargingMoneyTaskPort(
		RechargingMoneyTask task
	);
}
