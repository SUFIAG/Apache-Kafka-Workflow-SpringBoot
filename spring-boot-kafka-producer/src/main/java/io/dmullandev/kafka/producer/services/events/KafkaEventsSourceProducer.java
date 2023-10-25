package io.dmullandev.kafka.producer.services.events;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import com.launchdarkly.eventsource.background.BackgroundEventSource;

import io.dmullandev.kafka.constants.KafkaAppConstants;
import io.dmullandev.kafka.producer.handlers.WikiMediaChangesHandler;

/**
 * Producer using an event source
 * 
 * @author dmullandev
 *
 */
@Service
public class KafkaEventsSourceProducer {

    private static final Logger LOG = LogManager.getLogger(KafkaEventsSourceProducer.class);

    private KafkaTemplate<String, String> kafkaTemplate;

    public KafkaEventsSourceProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws InterruptedException {
        final String url = "https://stream.wikimedia.org/v2/stream/recentchange";

        BackgroundEventHandler eventHandler = new WikiMediaChangesHandler(kafkaTemplate, KafkaAppConstants.APP_TOPIC_WIKIMEDIA);
        BackgroundEventSource.Builder builder = new BackgroundEventSource.Builder(eventHandler, new EventSource.Builder(URI.create(url)));

        BackgroundEventSource eventSource = builder.build();

        eventSource.start();

        TimeUnit.MINUTES.sleep(5);
    }

}
