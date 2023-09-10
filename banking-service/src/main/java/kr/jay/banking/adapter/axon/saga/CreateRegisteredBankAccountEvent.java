package kr.jay.banking.adapter.axon.saga;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CreateRegisteredBankAccountEvent
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/09/08
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRegisteredBankAccountEvent {
	private String membershipId;
	private String bankName;
	private String bankAccountNumber;
}
