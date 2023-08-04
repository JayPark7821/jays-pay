package kr.jay.banking.adapter.out.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * MembershipJpaEntity
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/02
 */
@Entity
@Table(name = "registered_bank_account")
@Data
@NoArgsConstructor
public class RegisteredBankAccountJpaEntity {

	@Id
	@GeneratedValue
	private Long registeredBankAccountId;
	private String membershipId;
	private String bankName;
	private String bankAccountNumber;
	private boolean linkStatusIsValid;

	public RegisteredBankAccountJpaEntity(
		final String membershipId,
		final String bankName,
		final String bankAccountNumber,
		final boolean linkStatusIsValid
	) {
		this.membershipId = membershipId;
		this.bankName = bankName;
		this.bankAccountNumber = bankAccountNumber;
		this.linkStatusIsValid = linkStatusIsValid;
	}

	@Override
	public String toString() {
		return "RegisteredBankAccountJpaEntity{" +
			"registeredBankAccountId=" + registeredBankAccountId +
			", membershipId='" + membershipId + '\'' +
			", bankName='" + bankName + '\'' +
			", bankAccountNumber='" + bankAccountNumber + '\'' +
			", linkStatusIsValid=" + linkStatusIsValid +
			'}';
	}
}
