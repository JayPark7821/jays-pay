package kr.jay.remittance.application.port.out.membership;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * MembershipStatus
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MembershipStatus {
	private String membershipId;
	private boolean isValid;
}
