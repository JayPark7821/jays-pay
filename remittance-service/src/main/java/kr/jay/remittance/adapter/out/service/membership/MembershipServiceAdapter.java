package kr.jay.remittance.adapter.out.service.membership;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.jay.common.CommonHttpClient;
import kr.jay.common.ExternalSystemAdapter;
import kr.jay.remittance.application.port.out.membership.MembershipPort;
import kr.jay.remittance.application.port.out.membership.MembershipStatus;
import lombok.RequiredArgsConstructor;

/**
 * MembershipAdapter
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/08
 */
@ExternalSystemAdapter
@RequiredArgsConstructor
public class MembershipServiceAdapter implements MembershipPort {

	private final CommonHttpClient membershipServiceHttpClient;

	@Value("${service.membership.url}")
	private String membershipServiceEndpoint;

	@Override
	public MembershipStatus getMembershipStatus(String membershipId) {

		String buildUrl = String.join("/", this.membershipServiceEndpoint, "membership", membershipId);
		try {
			String jsonResponse = membershipServiceHttpClient.sendGetRequest(buildUrl).body();
			ObjectMapper mapper = new ObjectMapper();

			Membership mem = mapper.readValue(jsonResponse, Membership.class);
			if (mem.isValid()){
				return new MembershipStatus(mem.getMembershipId(), true);
			} else{
				return new MembershipStatus(mem.getMembershipId(), false);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}