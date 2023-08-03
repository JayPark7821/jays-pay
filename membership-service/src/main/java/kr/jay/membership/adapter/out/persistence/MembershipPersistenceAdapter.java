package kr.jay.membership.adapter.out.persistence;

import org.springframework.stereotype.Repository;

import kr.jay.common.PersistenceAdapter;
import kr.jay.membership.application.port.out.FindMembershipPort;
import kr.jay.membership.application.port.out.ModifyMembershipPort;
import kr.jay.membership.application.port.out.RegisterMembershipPort;
import kr.jay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;

/**
 * MembershipPersistenceAdapter
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/02
 */
@Repository
@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements RegisterMembershipPort, FindMembershipPort, ModifyMembershipPort {

	private final SpringDataMembershipRepository membershipRepository;

	@Override
	public MembershipJpaEntity createMembership(final Membership.MembershipName membershipName,
		final Membership.MembershipEmail membershipEmail,
		final Membership.MembershipAddress membershipAddress,
		final Membership.MembershipIsValid membershipIsValid,
		final Membership.MembershipIsCorp membershipIsCorp
	) {
		return membershipRepository.save(
			new MembershipJpaEntity(
				membershipName.getNameValue(),
				membershipEmail.getEmailValue(),
				membershipAddress.getAddressValue(),
				membershipIsValid.isValidValue(),
				membershipIsCorp.isCorpValue()
			)
		);
	}

	@Override
	public MembershipJpaEntity findMembership(Membership.MembershipId membershipId) {
		return membershipRepository.getById(Long.parseLong(membershipId.getMembershipId()));
	}

	@Override
	public MembershipJpaEntity modifyMembership(Membership.MembershipId membershipId, Membership.MembershipName membershipName, Membership.MembershipEmail membershipEmail, Membership.MembershipAddress membershipAddress, Membership.MembershipIsValid membershipIsValid, Membership.MembershipIsCorp membershipIsCorp) {
		MembershipJpaEntity entity = membershipRepository.getById(Long.parseLong(membershipId.getMembershipId()));
		entity.setName(membershipName.getNameValue());
		entity.setAddress(membershipAddress.getAddressValue());
		entity.setEmail(membershipEmail.getEmailValue());
		entity.setCorp(membershipIsCorp.isCorpValue());
		entity.setValid(membershipIsValid.isValidValue());

		return membershipRepository.save(entity);
	}
}
