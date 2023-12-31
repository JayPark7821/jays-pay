package kr.jay.money.adapter.out.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.jay.common.RechargingMoneyTask;
import kr.jay.money.application.port.out.SendRechargingMoneyTaskPort;

/**
 * TaskProducer
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/07
 */

@Component
public class TaskProducer implements SendRechargingMoneyTaskPort {

	private final KafkaProducer<String, String> producer;
	private final String topic;

	public TaskProducer(
		@Value("${kafka.clusters.bootstrapservers}") final String bootstrapServers,
		@Value("${task.topic}") final String topic
	) {
		Properties props = new Properties();
		props.put("bootstrap.servers", bootstrapServers);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		this.producer = new KafkaProducer<>(props);
		this.topic = topic;
	}

	@Override
	public void sendRechargingMoneyTaskPort(final RechargingMoneyTask task) {
		this.sendMessage(task.getTaskID(), task);

	}

	public void sendMessage(String key, RechargingMoneyTask value) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonStringToProduce;
		// jsonString
		try {
			jsonStringToProduce = mapper.writeValueAsString(value);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, jsonStringToProduce);
		producer.send(record, (metadata, exception) -> {
			if (exception == null) {
				// System.out.println("Message sent successfully. Offset: " + metadata.offset());
			} else {
				exception.printStackTrace();
				// System.err.println("Failed to send message: " + exception.getMessage());
			}
		});
	}

}
