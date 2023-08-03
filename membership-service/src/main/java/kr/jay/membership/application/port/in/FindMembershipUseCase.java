package kr.jay.membership.application.port.in;

import kr.jay.membership.domain.Membership;

public interface FindMembershipUseCase {
	Membership findMembership(FindMembershipCommand command);
}