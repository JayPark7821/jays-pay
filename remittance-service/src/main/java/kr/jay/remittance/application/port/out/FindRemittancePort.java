package kr.jay.remittance.application.port.out;

import java.util.List;

import kr.jay.remittance.adapter.out.persistence.RemittanceRequestJpaEntity;
import kr.jay.remittance.application.port.in.FindRemittanceCommand;

/**
 * FindRemittancePort
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/08
 */
public interface FindRemittancePort {
	List<RemittanceRequestJpaEntity> findRemittanceHistory(FindRemittanceCommand command);
}
