package kr.jay.banking.adapter.out.external.bank;

import lombok.Data;

/**
 * GetBankAccountRequest
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/03
 */
@Data
public class GetBankAccountRequest {
	private String bankName;
	private String bankAccountNumber;

	public GetBankAccountRequest(final String bankName, final String bankAccountNumber) {
		this.bankName = bankName;
		this.bankAccountNumber = bankAccountNumber;
	}
}
