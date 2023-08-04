package kr.jay.banking.application.port.out;

import kr.jay.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import kr.jay.banking.domain.RegisteredBankAccount;
/**
 * RegisterMembershipPort
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/02
 */
public interface RegisterBankAccountPort {

	RegisteredBankAccountJpaEntity createRegisteredBankAccount(
		RegisteredBankAccount.MembershipId membershipId,
		RegisteredBankAccount.BankName bankName,
		RegisteredBankAccount.BankAccountNumber bankAccountNumber,
		RegisteredBankAccount.LinkStatusIsValid linkStatusIsValid
	);
}
