package kr.jay.remittance.adapter.in.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.jay.common.WebAdapter;
import kr.jay.remittance.application.port.in.RequestRemittanceCommand;
import kr.jay.remittance.application.port.in.RequestRemittanceUseCase;
import kr.jay.remittance.domain.RemittanceRequest;
import lombok.RequiredArgsConstructor;

/**
 * RequestRemittanceController
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/08
 */

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RequestRemittanceController {
	private final RequestRemittanceUseCase requestRemittanceUseCase;
	@PostMapping(path = "/remittance/request")
	RemittanceRequest requestRemittance(@RequestBody RequestRemittanceRequest request) {
		RequestRemittanceCommand command = RequestRemittanceCommand.builder()
			.fromMembershipId(request.getFromMembershipId())
			.toMembershipId(request.getToMembershipId())
			.toBankName(request.getToBankName())
			.toBankAccountNumber(request.getToBankAccountNumber())
			.amount(request.getAmount())
			.remittanceType(request.getRemittanceType())
			.build();

		return requestRemittanceUseCase.requestRemittance(command);
	}
}
