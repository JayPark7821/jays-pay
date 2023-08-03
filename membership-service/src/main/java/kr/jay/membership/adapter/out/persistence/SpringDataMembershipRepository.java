package kr.jay.membership.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * SpringDataMembershipRepository
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/02
 */
public interface SpringDataMembershipRepository extends JpaRepository<MembershipJpaEntity, Long> {
}
