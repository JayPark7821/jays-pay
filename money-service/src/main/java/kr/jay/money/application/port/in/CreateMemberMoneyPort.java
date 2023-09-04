package kr.jay.money.application.port.in;

import kr.jay.money.domain.MemberMoney;

/**
 * CreateMemberMoneyPort
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/09/04
 */
public interface CreateMemberMoneyPort {

	void createMemberMoney(
		MemberMoney.MembershipId membershipId,
		MemberMoney.MoneyAggregateIdentifier aggregateIdentifier
	);
}
