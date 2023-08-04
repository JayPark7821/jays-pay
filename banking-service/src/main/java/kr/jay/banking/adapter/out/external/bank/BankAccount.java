package kr.jay.banking.adapter.out.external.bank;

import lombok.Data;

/**
 * BankAccount
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/03
 */
@Data
public class BankAccount {
	private String bankName;
	private String bankAccountNumber;
	private boolean isValid;

	public BankAccount(final String bankName, final String bankAccountNumber, final boolean isValid) {
		this.bankName = bankName;
		this.bankAccountNumber = bankAccountNumber;
		this.isValid = isValid;
	}
}
