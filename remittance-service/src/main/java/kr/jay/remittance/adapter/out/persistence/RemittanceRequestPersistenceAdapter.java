package kr.jay.remittance.adapter.out.persistence;

import java.util.List;

import kr.jay.common.PersistenceAdapter;
import kr.jay.remittance.application.port.in.FindRemittanceCommand;
import kr.jay.remittance.application.port.in.RequestRemittanceCommand;
import kr.jay.remittance.application.port.out.FindRemittancePort;
import kr.jay.remittance.application.port.out.RequestRemittancePort;
import lombok.RequiredArgsConstructor;

/**
 * RemittanceRequestPersistenceAdapter
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/08
 */
@PersistenceAdapter
@RequiredArgsConstructor
public class RemittanceRequestPersistenceAdapter implements RequestRemittancePort , FindRemittancePort {
	private final SpringDataRemittanceRequestRepository remittanceRequestRepository;
	@Override
	public RemittanceRequestJpaEntity createRemittanceRequestHistory(final RequestRemittanceCommand command) {
		return remittanceRequestRepository.save(RemittanceRequestJpaEntity.builder()
			.fromMembershipId(command.getFromMembershipId())
			.toMembershipId(command.getToMembershipId())
			.toBankName(command.getToBankName())
			.toBankAccountNumber(command.getToBankAccountNumber())
			.amount(command.getAmount())
			.remittanceType(command.getRemittanceType())
			.build());
	}

	@Override
	public boolean saveRemittanceRequestHistory(final RemittanceRequestJpaEntity entity) {
		remittanceRequestRepository.save(entity);
		return true;
	}

	@Override
	public List<RemittanceRequestJpaEntity> findRemittanceHistory(final FindRemittanceCommand command) {
		return null;
	}
}
