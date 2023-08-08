package kr.jay.banking.adapter.out.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

/**
 * Membership
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Membership {

	private  String membershipId;
	private  String name;
	private  String email;
	private  String address;
	private  boolean isValid;
	private  boolean isCorp;

}