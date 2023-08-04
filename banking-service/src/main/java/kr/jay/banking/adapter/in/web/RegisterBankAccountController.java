package kr.jay.banking.adapter.in.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.jay.banking.application.port.in.RegisterBankAccountCommand;
import kr.jay.banking.application.port.in.RegisterBankAccountUseCase;
import kr.jay.banking.domain.RegisteredBankAccount;
import kr.jay.common.WebAdapter;
import lombok.RequiredArgsConstructor;

/**
 * RegisterMembershipController
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/01
 */
@WebAdapter
@RestController
@RequiredArgsConstructor
public class RegisterBankAccountController {

	private final RegisterBankAccountUseCase registerBankAccountUseCase;
	@PostMapping(path = "/banking/account/register")
	RegisteredBankAccount registerMembership(@RequestBody RegisterBankAccountRequest request) {
		RegisterBankAccountCommand command = RegisterBankAccountCommand.builder()
			.membershipId(request.getMembershipId())
			.bankName(request.getBankName())
			.bankAccountNumber(request.getBankAccountNumber())
			.isValid(request.isValid())
			.build();

		final RegisteredBankAccount registeredBankAccount = registerBankAccountUseCase.registerBankAccount(command);
		if (registeredBankAccount == null) {
			// TODO
			return null;
		}
		return registeredBankAccount;
	}
}

