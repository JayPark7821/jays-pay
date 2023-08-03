package kr.jay.membership.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import kr.jay.common.WebAdapter;
import kr.jay.membership.application.port.in.FindMembershipCommand;
import kr.jay.membership.application.port.in.FindMembershipUseCase;
import kr.jay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;

/**
 * FindMembershipController
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/02
 */
@WebAdapter
@RestController
@RequiredArgsConstructor
public class FindMembershipController {

	private final FindMembershipUseCase findMembershipUseCase;

	@GetMapping(path = "/membership/{membershipId}")
	ResponseEntity<Membership> findMembershipByMemberId(@PathVariable String membershipId) {

		FindMembershipCommand command = FindMembershipCommand.builder()
			.membershipId(membershipId)
			.build();
		return ResponseEntity.ok(findMembershipUseCase.findMembership(command));
	}
}