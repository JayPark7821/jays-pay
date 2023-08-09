package kr.jay.remittance.adapter.out.service.membership;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Membership
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/08
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Membership {
	private String membershipId;
	private  String name;
	private  String email;
	private  String address;
	private  boolean isValid;
	private  boolean isCorp;

	@Override
	public String toString() {
		return "Membership from Remittance {" +
			"membershipId='" + membershipId + '\'' +
			", name='" + name + '\'' +
			", email='" + email + '\'' +
			", address='" + address + '\'' +
			", isValid=" + isValid +
			", isCorp=" + isCorp +
			'}';
	}
}
