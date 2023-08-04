package kr.jay.banking.adapter.out.persistence;

import org.springframework.stereotype.Component;

import kr.jay.banking.domain.RegisteredBankAccount;

/**
 * MembershipMapper
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/02
 */
@Component
public class RegisteredBankAccountMapper {
	public RegisteredBankAccount mapToDomainEntity(final RegisteredBankAccountJpaEntity registeredBankAccountJpaEntity) {
		return RegisteredBankAccount.generateRegisterBankAccount(
			new RegisteredBankAccount.RegisterBankAccountId(registeredBankAccountJpaEntity.getRegisteredBankAccountId() + ""),
			new RegisteredBankAccount.MembershipId(registeredBankAccountJpaEntity.getMembershipId()),
			new RegisteredBankAccount.BankName(registeredBankAccountJpaEntity.getBankName()),
			new RegisteredBankAccount.BankAccountNumber(registeredBankAccountJpaEntity.getBankAccountNumber()),
			new RegisteredBankAccount.LinkStatusIsValid(registeredBankAccountJpaEntity.isLinkStatusIsValid())
		);
	}
}
