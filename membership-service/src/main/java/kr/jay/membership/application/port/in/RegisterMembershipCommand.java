package kr.jay.membership.application.port.in;

import kr.jay.common.SelfValidating;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * RegisterMembershipCommand
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/02
 */
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class RegisterMembershipCommand extends SelfValidating<RegisterMembershipCommand> {

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

	public RegisterMembershipCommand(
		final String name,
		final String email,
		final String address,
		final boolean isValid,
		final boolean isCorp
	) {
		this.name = name;
		this.email = email;
		this.address = address;
		this.isValid = isValid;
		this.isCorp = isCorp;

		this.validateSelf();
	}
}
