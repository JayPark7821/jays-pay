package kr.jay.remittance.application.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import kr.jay.common.UseCase;
import kr.jay.remittance.application.port.in.FindRemittanceCommand;
import kr.jay.remittance.application.port.in.FindRemittanceUseCase;
import kr.jay.remittance.application.port.out.FindRemittancePort;
import kr.jay.remittance.domain.RemittanceRequest;
import lombok.RequiredArgsConstructor;

/**
 * FindRemittanceService
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/08
 */

@UseCase
@RequiredArgsConstructor
@Transactional
public class FindRemittanceService implements FindRemittanceUseCase {
	private final FindRemittancePort findRemittancePort;
	@Override
	public List<RemittanceRequest> findRemittanceHistory(final FindRemittanceCommand command) {
		findRemittancePort.findRemittanceHistory(command);
		return null;
	}
}
