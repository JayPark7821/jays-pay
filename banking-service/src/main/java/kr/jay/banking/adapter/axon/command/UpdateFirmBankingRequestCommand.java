package kr.jay.banking.adapter.axon.command;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * CreateFirmBankingRequestCommand
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/09/07
 */

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFirmBankingRequestCommand {
	@NotNull
	private String aggregateIdentifier;

	private int firmBankingStatus;
}
