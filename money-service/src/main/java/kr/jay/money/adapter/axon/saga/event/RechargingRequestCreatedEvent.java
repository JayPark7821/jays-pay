package kr.jay.money.adapter.axon.saga.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RechargingRequestCreatedEvent
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/09/08
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RechargingRequestCreatedEvent {
	// 고객의 충전 요청이 생성되었을 때 발생하는 이벤트

	private String rechargingRequestId;
	private String membershipId;
	private int amount;

}
