package kr.jay.remittance.application.port.in;

import java.util.List;

import kr.jay.remittance.domain.RemittanceRequest;

/**
 * FindRemittanceUseCase
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/08
 */
public interface FindRemittanceUseCase {
	List<RemittanceRequest> findRemittanceHistory(FindRemittanceCommand command);
}
