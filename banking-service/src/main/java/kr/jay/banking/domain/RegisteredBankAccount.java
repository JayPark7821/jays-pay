package kr.jay.banking.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

/**
 * Membership
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/01
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisteredBankAccount {
	@Getter
	private final String registerBankAccountId;
	@Getter
	private final String membershipId;
	@Getter
	private final String bankName;
	@Getter
	private final String bankAccountNumber;
	@Getter
	private final boolean linkStatusIsValid;

	public static RegisteredBankAccount generateRegisterBankAccount(
		RegisterBankAccountId registerBankAccountId,
		MembershipId membershipId,
		BankName bankName,
		BankAccountNumber bankAccountNumber,
		LinkStatusIsValid linkStatusIsValid
	) {
		return new RegisteredBankAccount(
			registerBankAccountId.registerBankAccountId,
			membershipId.membershipId,
			bankName.bankName,
			bankAccountNumber.bankAccountNumber,
			linkStatusIsValid.linkStatusIsValid
		);
	}

	@Value
	public static class RegisterBankAccountId {
		public RegisterBankAccountId(String value) {
			this.registerBankAccountId = value;
		}

		String registerBankAccountId;
	}

	@Value
	public static class MembershipId {
		public MembershipId(String value) {
			this.membershipId = value;
		}

		String membershipId;
	}

	@Value
	public static class BankName {
		public BankName(String value) {
			this.bankName = value;
		}

		String bankName;
	}

	@Value
	public static class BankAccountNumber {
		public BankAccountNumber(String value) {
			this.bankAccountNumber = value;
		}

		String bankAccountNumber;
	}

	@Value
	public static class LinkStatusIsValid {
		public LinkStatusIsValid(boolean value) {
			this.linkStatusIsValid = value;
		}

		boolean linkStatusIsValid;
	}

}