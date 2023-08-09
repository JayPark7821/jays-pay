package kr.jay.remittance.application.port.out.money;

/**
 * MoneyPort
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/08
 */
public interface MoneyPort {
	MoneyInfo getMoneyInfo(String membershipId);
	boolean requestMoneyRecharging(String membershipId, int amount);
	boolean requestMoneyIncrease(String membershipId, int amount);
	boolean requestMoneyDecrease(String membershipId, int amount);

}
