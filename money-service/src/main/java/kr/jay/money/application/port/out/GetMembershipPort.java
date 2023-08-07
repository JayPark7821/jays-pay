package kr.jay.money.application.port.out;

/**
 * GetMembershipPort
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/07
 */
public interface GetMembershipPort {
	 MembershipStatus getMembership(String membershipId);
}
