package kr.jay.remittance.application.port.out.membership;

/**
 * MembershipPort
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/08
 */
public interface MembershipPort {
	MembershipStatus getMembershipStatus(String membershipId);
}
