package kr.jay.membership.adapter.out.persistence;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * MembershipJpaEntity
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/02
 */
@Entity
@Table(name = "membership")
@Data
@NoArgsConstructor
public class MembershipJpaEntity {

	@Id
	@GeneratedValue
	private Long memberId;
	private String name;
	private String address;
	private String email;
	private boolean isValid;
	private boolean isCorp;

	public MembershipJpaEntity(
		final String name,
		final String address,
		final String email,
		final boolean isValid,
		final boolean isCorp
	) {
		this.name = name;
		this.address = address;
		this.email = email;
		this.isValid = isValid;
		this.isCorp = isCorp;
	}

	@Override
	public String toString() {
		return "MembershipJpaEntity{" +
			"memberId=" + memberId +
			", name='" + name + '\'' +
			", address='" + address + '\'' +
			", email='" + email + '\'' +
			", isValid=" + isValid +
			", isCorp=" + isCorp +
			'}';
	}
}
