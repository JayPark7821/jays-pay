package kr.jay.remittance.application.port.out.money;

import lombok.Data;

/**
 * MonyInfo
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/08
 */
//  송금서비스에서 필요한 머니의 정보
@Data
public class MoneyInfo {

	private String membershipId;
	private int balance;
}
