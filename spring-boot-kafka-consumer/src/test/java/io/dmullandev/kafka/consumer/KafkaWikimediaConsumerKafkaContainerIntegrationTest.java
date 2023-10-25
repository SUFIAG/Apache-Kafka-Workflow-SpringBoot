package io.dmullandev.kafka.consumer;

import static org.awaitility.Awaitility.await;
import java.time.Duration;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import io.dmullandev.kafka.constants.KafkaAppConstants;
import io.dmullandev.kafka.consumer.entity.WikimediaData;
import io.dmullandev.kafka.consumer.repository.WikimediaDataRepository;

/**
 * Test class for {@link KafkaWikimediaConsumer}
 * 
 * @author dmullandev
 *
 */
@SpringBootTest
@TestPropertySource(properties = {
                "spring.kafka.consumer.auto-offset-reset=earliest",
                "spring.datasource.url=jdbc:tc:mysql:8.0.32:///db",
})
@Testcontainers
@EnableAutoConfiguration
public class KafkaWikimediaConsumerKafkaContainerIntegrationTest {
    private static final Logger LOG = LogManager.getLogger(KafkaWikimediaConsumerKafkaContainerIntegrationTest.class);
    private static final Long TEST_WIKIMEDIA_EVENT_ID_1 = 1L;
    private static final String TEST_WIKIMEDIA_EVENT_DATA_1 = "TEST_WIKIMEDIA_EVENT_DATA_1";
    private static final String TEST_WIKIMEDIA_EVENT_DATA_2 = "TEST_WIKIMEDIA_EVENT_DATA_2";

    @Container
    public static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.5.0"));

    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
    }

    @Autowired
    private WikimediaDataRepository wikimediaDataRepository;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @BeforeEach
    void setUp() {
        WikimediaData eventData = new WikimediaData();
        eventData.setId(TEST_WIKIMEDIA_EVENT_ID_1);
        eventData.setWikimediaEventData(TEST_WIKIMEDIA_EVENT_DATA_1);
        wikimediaDataRepository.save(eventData);
    }

    @Test
    void shouldHandleConsumingWikimediaAndDBSave() {
        kafkaTemplate.send(KafkaAppConstants.APP_TOPIC_WIKIMEDIA, TEST_WIKIMEDIA_EVENT_DATA_2);

        await().pollInterval(Duration.ofSeconds(3))
               .atMost(Duration.ofSeconds(10))
               .untilAsserted(() -> {
                   LOG.info("shouldHandleConsumingWikimediaAndDBSave polling...");
                   Optional<WikimediaData> optWikimediaData = wikimediaDataRepository.findById(2L);
                   org.assertj.core.api.Assertions.assertThat(optWikimediaData).isPresent();
                   org.assertj.core.api.Assertions.assertThat(
                                   optWikimediaData.get().getWikimediaEventData().equals(TEST_WIKIMEDIA_EVENT_DATA_2));
               });
    }
}
