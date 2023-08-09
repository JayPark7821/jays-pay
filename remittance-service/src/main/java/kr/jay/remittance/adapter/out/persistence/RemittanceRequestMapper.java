package kr.jay.remittance.adapter.out.persistence;

import java.util.UUID;

import org.springframework.stereotype.Component;

import kr.jay.remittance.domain.RemittanceRequest;

/**
 * RemittanceRequestMapper
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/08
 */
@Component
public class RemittanceRequestMapper {

	public RemittanceRequest mapToDomainEntity(RemittanceRequestJpaEntity remittanceRequestJpaEntity) {
		return RemittanceRequest.generateRemittanceRequest(
			new RemittanceRequest.RemittanceRequestId(remittanceRequestJpaEntity.getFromMembershipId()),
			new RemittanceRequest.RemittanceFromMembershipId(remittanceRequestJpaEntity.getFromMembershipId()),
			new RemittanceRequest.ToBankName(remittanceRequestJpaEntity.getToBankName()),
			new RemittanceRequest.ToBankAccountNumber(remittanceRequestJpaEntity.getToBankAccountNumber()),
			new RemittanceRequest.RemittanceType(remittanceRequestJpaEntity.getRemittanceType()),
			new RemittanceRequest.Amount(remittanceRequestJpaEntity.getAmount()),
			new RemittanceRequest.RemittanceStatus(remittanceRequestJpaEntity.getRemittanceStatus())
		);
	}
}
