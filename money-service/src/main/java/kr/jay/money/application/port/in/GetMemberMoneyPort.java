package kr.jay.money.application.port.in;

import kr.jay.money.adapter.out.persistence.MemberMoneyJpaEntity;
import kr.jay.money.domain.MemberMoney;

/**
 * GetMemberMoneyPort
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/09/05
 */
public interface GetMemberMoneyPort {

	MemberMoneyJpaEntity getMemberMoney(
		MemberMoney.MembershipId memberId
	);
}
