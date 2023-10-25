package io.dmullandev.kafka.consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import io.dmullandev.kafka.constants.KafkaAppConstants;
import io.dmullandev.kafka.consumer.entity.WikimediaData;
import io.dmullandev.kafka.consumer.repository.WikimediaDataRepository;

/**
 * Consumer for wikimedia events on the wikimedia topic.
 * 
 * @author dmullandev
 *
 */
@Service
public class KafkaWikimediaConsumer {

    private static final Logger LOG = LogManager.getLogger(KafkaWikimediaConsumer.class);

    private WikimediaDataRepository wikimediaDataRepository;

    public KafkaWikimediaConsumer(WikimediaDataRepository wikimediaDataRepository) {
        this.wikimediaDataRepository = wikimediaDataRepository;
    }

    @KafkaListener(topics = KafkaAppConstants.APP_TOPIC_WIKIMEDIA, groupId = KafkaAppConstants.APP_TOPIC_BASE + "-group")
    void listenerWikimediaEvent(String wikimediaEvent) {
        LOG.info("Receiving wikimediaEvent: {}", wikimediaEvent);
        WikimediaData wikimediaData = new WikimediaData();
        wikimediaData.setWikimediaEventData(wikimediaEvent);

        wikimediaDataRepository.save(wikimediaData);
    }
}