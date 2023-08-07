package kr.jay.common;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.springframework.context.annotation.Configuration;

/**
 * CountDownLatchManager
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/07
 */
@Configuration
public class CountDownLatchManager {
	private final Map<String, CountDownLatch> countDownLatchMap;
	private final Map<String, String> stringMap;

	public CountDownLatchManager() {
		this.countDownLatchMap = new HashMap<>();
		this.stringMap = new HashMap<>();
	}

	public void addCountDownLatch(String key) {
		this.countDownLatchMap.put(key, new CountDownLatch(1));
	}

	public void setDataForKey(String key, String data){
		this.stringMap.put(key, data);
	}
	public String getDataForKey(String key){
		return this.stringMap.get(key);
	}
	public CountDownLatch getCountDownLatch(String key) {
		return this.countDownLatchMap.get(key);
	}
}
