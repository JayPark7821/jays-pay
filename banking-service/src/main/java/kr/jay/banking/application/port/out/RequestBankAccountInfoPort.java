package kr.jay.banking.application.port.out;

import kr.jay.banking.adapter.out.external.bank.BankAccount;
import kr.jay.banking.adapter.out.external.bank.GetBankAccountRequest;

/**
 * RequestBankAccountInfoPort
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/03
 */
public interface RequestBankAccountInfoPort {
	BankAccount getBankAccountInfo(GetBankAccountRequest request);
}
