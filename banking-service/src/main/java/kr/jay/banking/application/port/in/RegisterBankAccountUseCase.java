package kr.jay.banking.application.port.in;

import kr.jay.banking.domain.RegisteredBankAccount;

/**
 * RegisterMembershipUseCase
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/02
 */

public interface RegisterBankAccountUseCase {
	RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command);
}
