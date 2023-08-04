package kr.jay.banking.adapter.out.persistence;

import org.springframework.stereotype.Repository;

import kr.jay.banking.application.port.out.RegisterBankAccountPort;
import kr.jay.banking.domain.RegisteredBankAccount;
import kr.jay.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

/**
 * MembershipPersistenceAdapter
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/02
 */
@Repository
@PersistenceAdapter
@RequiredArgsConstructor
public class RegisteredBankAccountPersistenceAdapter implements RegisterBankAccountPort {

	private final SpringDataRegisteredBankAccountRepository bankAccountRepository;

	@Override
	public RegisteredBankAccountJpaEntity createRegisteredBankAccount(
		final RegisteredBankAccount.MembershipId membershipId,
		final RegisteredBankAccount.BankName bankName,
		final RegisteredBankAccount.BankAccountNumber bankAccountNumber,
		final RegisteredBankAccount.LinkStatusIsValid linkStatusIsValid
	) {
		return bankAccountRepository.save(
			new RegisteredBankAccountJpaEntity(
				membershipId.getMembershipId(),
				bankName.getBankName(),
				bankAccountNumber.getBankAccountNumber(),
				linkStatusIsValid.isLinkStatusIsValid()
			)
		);
	}
}
