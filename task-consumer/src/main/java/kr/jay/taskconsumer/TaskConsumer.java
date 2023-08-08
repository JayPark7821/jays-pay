package kr.jay.taskconsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.jay.common.RechargingMoneyTask;
import kr.jay.common.SubTask;
import lombok.extern.slf4j.Slf4j;

/**
 * TaskConsumer
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/07
 */

@Slf4j
@Component
public class TaskConsumer {

	private final KafkaConsumer<String, String> consumer;
	private final TaskResultProducer taskResultProducer;
	public TaskConsumer(@Value("${kafka.clusters.bootstrapservers}") String bootstrapServers,
		@Value("${task.topic}")String topic,
		final TaskResultProducer taskResultProducer
	) {
		this.taskResultProducer = taskResultProducer;

		Properties props = new Properties();

		props.put("bootstrap.servers", bootstrapServers);

		// consumer group
		props.put("group.id", "my-group");

		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		this.consumer = new KafkaConsumer<>(props);

		consumer.subscribe(Collections.singletonList(topic));
		Thread consumerThread = new Thread(() -> {
			try {
				while (true) {
					ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
					ObjectMapper mapper = new ObjectMapper();

					for (ConsumerRecord<String, String> record : records) {
						// record: RechargingMoneyTask (jsonString)
						RechargingMoneyTask task;
						// task run
						try {
							log.info("============ read task message ============");
							task = mapper.readValue(record.value(), RechargingMoneyTask.class);
						} catch (JsonProcessingException e) {
							throw new RuntimeException(e);
						}
						// task result
						for (SubTask subTask : task.getSubTaskList()){
							// what subtask, membership, banking
							// external port, adapter 호출후 판단 해야함.

							// all subtask is done. true
							subTask.setStatus("success");
						}


						// produce TaskResult
						this.taskResultProducer.sendTaskResult(task.getTaskID(), task);

					}
				}
			} finally {
				consumer.close();
			}
		});
		consumerThread.start();
	}
}
