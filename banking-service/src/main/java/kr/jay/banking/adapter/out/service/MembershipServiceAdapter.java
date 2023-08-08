package kr.jay.banking.adapter.out.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.jay.banking.application.port.out.GetMembershipPort;
import kr.jay.banking.application.port.out.MembershipStatus;
import kr.jay.common.CommonHttpClient;
import lombok.extern.slf4j.Slf4j;

/**
 * MembershipServiceAdapter
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/07
 */
@Slf4j
@Component
public class MembershipServiceAdapter implements GetMembershipPort {

	private final CommonHttpClient commonHttpClient;
	private final String membershipServiceUrl;
	private final ObjectMapper objectMapper;

	public MembershipServiceAdapter(
		final CommonHttpClient commonHttpClient,
		@Value("${service.membership.url}") final String membershipServiceUrl,
		final ObjectMapper objectMapper
	) {
		this.commonHttpClient = commonHttpClient;
		this.membershipServiceUrl = membershipServiceUrl;
		this.objectMapper = objectMapper;
	}

	@Override
	public MembershipStatus getMembership(final String membershipId) {
		String url = String.join("/", membershipServiceUrl, "membership", membershipId);
		log.info("url: {}", url);
		try {
			final String jsonResponse = commonHttpClient.sendGetRequest(url).body();
			final Membership membership = objectMapper.readValue(jsonResponse, Membership.class);
			if (membership.isValid()) {
				return new MembershipStatus(membership.getMembershipId(), true);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return null;
	}
}
