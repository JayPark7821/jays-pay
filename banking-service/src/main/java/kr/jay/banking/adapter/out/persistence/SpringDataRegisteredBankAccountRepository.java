package kr.jay.banking.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * SpringDataMembershipRepository
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/02
 */
public interface SpringDataRegisteredBankAccountRepository extends JpaRepository<RegisteredBankAccountJpaEntity, Long> {
}
