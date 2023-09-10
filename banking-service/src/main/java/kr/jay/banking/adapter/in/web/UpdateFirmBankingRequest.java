package kr.jay.banking.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UpdateFirmBankingRequest
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/09/07
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFirmBankingRequest {
	private String firmBankingRequestAggregateIdentifier;
	private int status;
}
