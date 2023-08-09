package kr.jay.remittance.application.port.out.banking;

/**
 * BankingPort
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/08
 */
public interface BankingPort {
	 BankingInfo getMembershipBankingInfo(String bankName, String bankAccountNumber);

	boolean requestFirmbanking(String bankName, String bankAccountNumber, int amount);
}
