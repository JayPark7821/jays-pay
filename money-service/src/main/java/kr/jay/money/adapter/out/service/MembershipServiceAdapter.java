package kr.jay.money.adapter.out.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.jay.common.CommonHttpClient;
import kr.jay.money.application.port.out.GetMembershipPort;
import kr.jay.money.application.port.out.MembershipStatus;

/**
 * MembershipServiceAdapter
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/07
 */
@Component
public class MembershipServiceAdapter implements GetMembershipPort {

	private final CommonHttpClient commonHttpClient;

	private final String membershipServiceUrl;

	public MembershipServiceAdapter(CommonHttpClient commonHttpClient,
		@Value("${service.membership.url}") String membershipServiceUrl) {
		this.commonHttpClient = commonHttpClient;
		this.membershipServiceUrl = membershipServiceUrl;
	}

	@Override
	public MembershipStatus getMembership(String membershipId) {

		String url = String.join("/", membershipServiceUrl, "membership", membershipId);
		try {
			String jsonResponse = commonHttpClient.sendGetRequest(url).body();
			// json Membership

			ObjectMapper mapper = new ObjectMapper();
			Membership membership = mapper.readValue(jsonResponse, Membership.class);

			if (membership.isValid()){
				return new MembershipStatus(membership.getMembershipId(), true);
			} else {
				return new MembershipStatus(membership.getMembershipId(), false);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
