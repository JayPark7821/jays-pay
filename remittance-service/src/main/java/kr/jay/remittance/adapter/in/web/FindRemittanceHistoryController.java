package kr.jay.remittance.adapter.in.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import kr.jay.common.WebAdapter;
import kr.jay.remittance.application.port.in.FindRemittanceCommand;
import kr.jay.remittance.application.port.in.FindRemittanceUseCase;
import kr.jay.remittance.domain.RemittanceRequest;
import lombok.RequiredArgsConstructor;

/**
 * FindRemittanceHistoryController
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/08
 */
@WebAdapter
@RestController
@RequiredArgsConstructor
public class FindRemittanceHistoryController {

	private final FindRemittanceUseCase findRemittanceUseCase;
	@GetMapping( "/remittance/{membershipId}")
	List<RemittanceRequest> findRemittanceHistory(@PathVariable String membershipId) {
		FindRemittanceCommand command = FindRemittanceCommand.builder()
			.membershipId(membershipId)
			.build();

		return findRemittanceUseCase.findRemittanceHistory(command);
	}
}
