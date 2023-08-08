package kr.jay.money.adapter.out.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Membership
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Membership {
	private String membershipId;
	private String name;
	private String email;
	private String address;
	private boolean isValid;
	private boolean isCorp;
}

