package kr.jay.banking.adapter.axon.saga;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * CreateRegisteredBankAccountCommand
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/09/08
 */

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class CreateRegisteredBankAccountCommand {

	private String membershipId;
	private String bankName;
	private String bankAccountNumber;

}
