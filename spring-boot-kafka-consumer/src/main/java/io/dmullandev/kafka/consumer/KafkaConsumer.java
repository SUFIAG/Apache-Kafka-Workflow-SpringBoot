package io.dmullandev.kafka.consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import io.dmullandev.kafka.constants.KafkaAppConstants;

/**
 * Consumer for timestamp strings on the timestamp topic.
 * 
 * @author dmullandev
 *
 */
@Component
public class KafkaConsumer {
    private static final Logger LOG = LogManager.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = KafkaAppConstants.APP_TOPIC_TIMESTAMP, groupId = KafkaAppConstants.APP_TOPIC_BASE + "-group")
    void listener(String timestamp) {
        LOG.info("Receiving String: {}", timestamp);
    }

}