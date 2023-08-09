package kr.jay.remittance.application.port.in;

import kr.jay.remittance.domain.RemittanceRequest;

/**
 * RequestRemittanceUseCase
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/08
 */
public interface RequestRemittanceUseCase {
	RemittanceRequest requestRemittance(kr.jay.remittance.application.port.in.RequestRemittanceCommand command);
}
