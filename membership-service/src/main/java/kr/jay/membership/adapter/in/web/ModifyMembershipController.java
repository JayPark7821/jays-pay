package kr.jay.membership.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.jay.common.WebAdapter;
import kr.jay.membership.application.port.in.ModifyMembershipCommand;
import kr.jay.membership.application.port.in.ModifyMembershipUseCase;
import kr.jay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;

/**
 * ModifyMembershipController
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/03
 */
@WebAdapter
@RestController
@RequiredArgsConstructor
public class ModifyMembershipController {

	private final ModifyMembershipUseCase modifyMembershipUseCase;

	@PostMapping(path = "/membership/modify/{membershipId}")
	ResponseEntity<Membership> modifyMembershipByMemberId(
		@RequestBody ModifyMembershipRequest request
	) {

		ModifyMembershipCommand command = ModifyMembershipCommand.builder()
			.membershipId(request.getMembershipId())
			.name(request.getName())
			.email(request.getEmail())
			.address(request.getAddress())
			.isValid(request.isValid())
			.isCorp(request.isCorp())
			.build();

		return ResponseEntity.ok(modifyMembershipUseCase.modifyMembership( command));
	}
}
