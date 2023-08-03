package kr.jay.membership.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ModifyMembershipRequest
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/03
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifyMembershipRequest {
	private String membershipId;
	private String name;
	private String address;
	private String email;
	private boolean isCorp;
	private boolean isValid;

}
