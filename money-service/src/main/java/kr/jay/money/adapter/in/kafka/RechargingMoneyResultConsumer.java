package kr.jay.money.adapter.in.kafka;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.validation.constraints.NotNull;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.jay.common.CountDownLatchManager;
import kr.jay.common.LoggingProducer;
import kr.jay.common.RechargingMoneyTask;
import kr.jay.common.SubTask;
import lombok.extern.slf4j.Slf4j;

/**
 * RechargingMoneyResultConsumer
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/07
 */

@Slf4j
@Component
public class RechargingMoneyResultConsumer {
	private final KafkaConsumer<String, String> consumer;

	private final LoggingProducer loggingProducer;
	@NotNull
	private final CountDownLatchManager countDownLatchManager;
	public RechargingMoneyResultConsumer(
		@Value("${kafka.clusters.bootstrapservers}") String bootstrapServers,
		@Value("${task.result.topic}")String topic,
		LoggingProducer loggingProducer,
		CountDownLatchManager countDownLatchManager
	) {
		this.loggingProducer = loggingProducer;
		this.countDownLatchManager = countDownLatchManager;
		Properties props = new Properties();
		props.put("bootstrap.servers", bootstrapServers);
		props.put("group.id", "my-group");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		this.consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Collections.singletonList(topic));

		Thread consumerThread = new Thread(() -> {
			try {
				ObjectMapper mapper = new ObjectMapper();
				while (true) {
					ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
					for (ConsumerRecord<String, String> record : records) {
						System.out.println("Received message: " + record.key()  + " / "+  record.value());
						// record: RechargingMoneyTask, ( all subtask is done)


						RechargingMoneyTask task;
						try {
							task = mapper.readValue(record.value(), RechargingMoneyTask.class);
						} catch (JsonProcessingException e) {
							throw new RuntimeException(e);
						}
						List<SubTask> subTaskList = task.getSubTaskList();

						boolean taskResult = true;
						// validation membership
						// validation banking
						for (SubTask subTask : subTaskList) {
							// 한번만 실패해도 실패한 task 로 간주.
							if (subTask.getStatus().equals("fail")) {
								taskResult = false;
								break;
							}
						}
						log.info("===========================================result consumer");

						// 모두 정상적으로 성공했다면
						if (taskResult) {
							this.loggingProducer.sendMessage(task.getTaskID(), "task success");
							this.countDownLatchManager.setDataForKey(task.getTaskID(), "success");
						} else{
							this.loggingProducer.sendMessage(task.getTaskID(), "task failed");
							this.countDownLatchManager.setDataForKey(task.getTaskID(), "failed");
						}

						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							throw new RuntimeException(e);
						}

						this.countDownLatchManager.getCountDownLatch(task.getTaskID()).countDown();
					}
				}
			} finally {
				consumer.close();
			}
		});
		consumerThread.start();
	}
}
