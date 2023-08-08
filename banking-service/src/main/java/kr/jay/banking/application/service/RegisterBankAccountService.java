package kr.jay.banking.application.service;

import org.springframework.transaction.annotation.Transactional;

import kr.jay.banking.adapter.out.external.bank.BankAccount;
import kr.jay.banking.adapter.out.external.bank.GetBankAccountRequest;
import kr.jay.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import kr.jay.banking.adapter.out.persistence.RegisteredBankAccountMapper;
import kr.jay.banking.application.port.in.RegisterBankAccountCommand;
import kr.jay.banking.application.port.in.RegisterBankAccountUseCase;
import kr.jay.banking.application.port.out.GetMembershipPort;
import kr.jay.banking.application.port.out.MembershipStatus;
import kr.jay.banking.application.port.out.RegisterBankAccountPort;
import kr.jay.banking.application.port.out.RequestBankAccountInfoPort;
import kr.jay.banking.domain.RegisteredBankAccount;
import kr.jay.common.UseCase;
import lombok.RequiredArgsConstructor;

/**
 * RegisterMembershipService
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/02
 */
@UseCase
@Transactional
@RequiredArgsConstructor
public class RegisterBankAccountService implements RegisterBankAccountUseCase {

	private final RegisterBankAccountPort registerBankAccountPort;
	private final RegisteredBankAccountMapper mapper;
	private final RequestBankAccountInfoPort requestBankAccountInfoPort;
	private final GetMembershipPort getMembershipPort;

	@Override
	public RegisteredBankAccount registerBankAccount(final RegisterBankAccountCommand command) {
		// 은행 계좌를 등록해야하는 서비스 (비즈니스 로직)
		// command.getMembershipId()

		// (멤버 서비스도 확인?)
		final MembershipStatus membership = getMembershipPort.getMembership(command.getMembershipId());
		if(!membership.isValid()) {
			// TODO
			return null;
		}
		// 1. 외부 실제 은행에 등록이 가능한 계좌인지(정상인지) 확인한다.
		// 외부의 은행에 이 계좌 정상인지? 확인을 해야해요.
		// Biz Logic -> External System
		// Port -> Adapter -> External System
		// Port
		// 실제 외부의 은행계좌 정보를 Get
		BankAccount accountInfo = requestBankAccountInfoPort.getBankAccountInfo(new GetBankAccountRequest(command.getBankName(), command.getBankAccountNumber()));
		boolean accountIsValid =  accountInfo.isValid();

		// 2. 등록가능한 계좌라면, 등록한다. 성공하면, 등록에 성공한 등록 정보를 리턴
		// 2-1. 등록가능하지 않은 계좌라면. 에러를 리턴
		if(accountIsValid) {
			// 등록 정보 저장
			RegisteredBankAccountJpaEntity savedAccountInfo = registerBankAccountPort.createRegisteredBankAccount(
				new RegisteredBankAccount.MembershipId(command.getMembershipId()+""),
				new RegisteredBankAccount.BankName(command.getBankName()),
				new RegisteredBankAccount.BankAccountNumber(command.getBankAccountNumber()),
				new RegisteredBankAccount.LinkStatusIsValid(command.isValid())
			);
			return mapper.mapToDomainEntity(savedAccountInfo);
		} else {
			return null;
		}
	}
}
