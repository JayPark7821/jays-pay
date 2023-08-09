package kr.jay.remittance.application.port.out;

import kr.jay.remittance.adapter.out.persistence.RemittanceRequestJpaEntity;
import kr.jay.remittance.application.port.in.RequestRemittanceCommand;

public interface RequestRemittancePort {

    RemittanceRequestJpaEntity createRemittanceRequestHistory(RequestRemittanceCommand command);
    boolean saveRemittanceRequestHistory(RemittanceRequestJpaEntity entity);
}
