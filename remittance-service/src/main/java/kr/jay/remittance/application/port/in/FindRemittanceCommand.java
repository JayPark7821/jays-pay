package kr.jay.remittance.application.port.in;

import javax.validation.constraints.NotNull;

import kr.jay.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * FindRemittanceCommand
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/08
 */
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class FindRemittanceCommand extends SelfValidating<FindRemittanceCommand> {

	@NotNull
	private String membershipId; // from membership

	public FindRemittanceCommand(String membershipId) {
		this.membershipId = membershipId;
		this.validateSelf();
	}
}