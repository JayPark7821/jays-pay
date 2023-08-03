package kr.jay.membership.adapter.in.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.jay.common.WebAdapter;
import kr.jay.membership.application.port.in.RegisterMembershipCommand;
import kr.jay.membership.application.port.in.RegisterMembershipUseCase;
import kr.jay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;

/**
 * RegisterMembershipController
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/01
 */
@WebAdapter
@RestController
@RequiredArgsConstructor
public class RegisterMembershipController {

	private final RegisterMembershipUseCase registerMembershipUseCase;

	@PostMapping("/membership/register")
	Membership registerMembership(@RequestBody RegisterMembershipRequest request) {
		RegisterMembershipCommand command = RegisterMembershipCommand.builder()
			.name(request.getName())
			.email(request.getEmail())
			.address(request.getAddress())
			.isValid(true)
			.isCorp(request.isCorp())
			.build();

		return registerMembershipUseCase.registerMembership(command);
	}


}
