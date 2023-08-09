package kr.jay.remittance.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * SpringDataRemittanceRequestRepository
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/08
 */
public interface SpringDataRemittanceRequestRepository extends JpaRepository<RemittanceRequestJpaEntity, Long> {
}
