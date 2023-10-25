package io.dmullandev.kafka.producer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.dmullandev.kafka.producer.services.events.KafkaEventsSourceProducer;

@SpringBootApplication
@EnableScheduling
public class KafkaProducerApplication implements CommandLineRunner {

    private KafkaEventsSourceProducer kafkaEventsSourceProducer;

    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerApplication.class, args);
    }

    public KafkaProducerApplication(KafkaEventsSourceProducer kafkaEventsSourceProducer) {
        this.kafkaEventsSourceProducer = kafkaEventsSourceProducer;
    }

    @Override
    public void run(String... args) throws Exception {
        kafkaEventsSourceProducer.sendMessage();
    }
}