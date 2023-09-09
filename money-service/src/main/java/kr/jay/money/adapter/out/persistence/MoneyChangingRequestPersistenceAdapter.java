package kr.jay.money.adapter.out.persistence;

import kr.jay.common.PersistenceAdapter;
import kr.jay.money.application.port.in.CreateMemberMoneyPort;
import kr.jay.money.application.port.in.GetMemberMoneyPort;
import kr.jay.money.application.port.out.IncreaseMoneyPort;
import kr.jay.money.domain.MemberMoney;
import kr.jay.money.domain.MoneyChangingRequest;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@PersistenceAdapter
@RequiredArgsConstructor
public class MoneyChangingRequestPersistenceAdapter implements IncreaseMoneyPort, CreateMemberMoneyPort, GetMemberMoneyPort {

	private final SpringDataMoneyChangingRequestRepository moneyChangingRequestRepository;

	private final SpringDataMemberMoneyRepository memberMoneyRepository;

	@Override
	public MoneyChangingRequestJpaEntity createMoneyChangingRequest(
		MoneyChangingRequest.TargetMembershipId targetMembershipId,
		MoneyChangingRequest.MoneyChangingType moneyChangingType,
		MoneyChangingRequest.ChangingMoneyAmount changingMoneyAmount,
		MoneyChangingRequest.MoneyChangingStatus moneyChangingStatus, MoneyChangingRequest.Uuid uuid) {
		return moneyChangingRequestRepository.save(
			new MoneyChangingRequestJpaEntity(
				targetMembershipId.getTargetMembershipId(),
				moneyChangingType.getMoneyChangingType(),
				changingMoneyAmount.getChangingMoneyAmount(),
				new Timestamp(System.currentTimeMillis()), // TODO: 2021-08-17 11:00:00
				moneyChangingStatus.getChangingMoneyStatus(),
				UUID.randomUUID()
			)
		);
	}

	@Override
	public MemberMoneyJpaEntity increaseMoney(MemberMoney.MembershipId memberId, int increaseMoneyAmount) {
		MemberMoneyJpaEntity entity;
		try {
			List<MemberMoneyJpaEntity> entityList = memberMoneyRepository.findByMembershipId(
				Long.parseLong(memberId.getMembershipId()));
			entity = entityList.get(0);

			entity.setBalance(entity.getBalance() + increaseMoneyAmount);
			return memberMoneyRepository.save(entity);
		} catch (Exception e) {
			entity = new MemberMoneyJpaEntity(
				Long.parseLong(memberId.getMembershipId()),
				increaseMoneyAmount,
				""
			);
			entity = memberMoneyRepository.save(entity);
			return entity;
		}

		//
		//        entity.setBalance(entity.getBalance() + increaseMoneyAmount);
		//        return  memberMoneyRepository.save(entity);
	}

	@Override
	public void createMemberMoney(
		final MemberMoney.MembershipId membershipId,
		final MemberMoney.MoneyAggregateIdentifier aggregateIdentifier
	) {
		MemberMoneyJpaEntity entity = new MemberMoneyJpaEntity(
			Long.parseLong(membershipId.getMembershipId()),
			0,
			aggregateIdentifier.getAggregateIdentifier()
		);

		memberMoneyRepository.save(entity);
	}

	@Override
	public MemberMoneyJpaEntity getMemberMoney(final MemberMoney.MembershipId memberId) {
		MemberMoneyJpaEntity entity;
		final List<MemberMoneyJpaEntity> entityList =
			memberMoneyRepository.findByMembershipId(Long.parseLong(memberId.getMembershipId()));
		if(entityList.isEmpty()) {
			entity = new MemberMoneyJpaEntity(
				Long.parseLong(memberId.getMembershipId()),
				0,
				""
			);
			return memberMoneyRepository.save(entity);
		}
		return entityList.get(0);
	}
}
