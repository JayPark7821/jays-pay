package kr.jay.membership.application.port.in;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import kr.jay.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ModifyMembershipCommand
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/03
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class ModifyMembershipCommand extends SelfValidating<RegisterMembershipCommand> {
	@NotNull
	private final String membershipId;
	@NotNull
	private final String name;
	@NotNull
	private final String email;
	@NotNull
	@NotBlank
	private final String address;
	@AssertTrue
	private final boolean isValid;

	private final boolean isCorp;

	public ModifyMembershipCommand(
		final String membershipId,
		final String name,
		final String email,
		final String address,
		final boolean isValid,
		final boolean isCorp
	) {
		this.membershipId = membershipId;
		this.name = name;
		this.email = email;
		this.address = address;
		this.isValid = isValid;
		this.isCorp = isCorp;

		this.validateSelf();
	}
}
