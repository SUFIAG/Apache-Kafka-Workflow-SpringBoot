package io.dmullandev.kafka.producer.controller;

import java.util.concurrent.CompletableFuture;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.dmullandev.kafka.constants.KafkaAppConstants;
import io.dmullandev.kafka.model.BusinessObject;

@RestController
@RequestMapping("api/v1/businessobjects")
public class KafkaProducerController {

    private static final Logger LOG = LogManager.getLogger(KafkaProducerController.class);
    
    private KafkaTemplate<String, BusinessObject> kafkaTemplateBusinessObject;

    public KafkaProducerController(KafkaTemplate<String, BusinessObject> kafkaTemplateBusinessObject) {
        this.kafkaTemplateBusinessObject = kafkaTemplateBusinessObject;
    }

    @PostMapping
    public void publish(@RequestBody BusinessObject businessObject) {
        LOG.info("Received business object {}", businessObject);
        CompletableFuture<SendResult<String, BusinessObject>> completableFuture = kafkaTemplateBusinessObject.send(KafkaAppConstants.APP_TOPIC_BUSINESSOBJECT,
                businessObject);

        completableFuture.whenComplete((result, throwable) -> {
            LOG.info("CompletableFuture: " + completableFuture + " - " + result.getRecordMetadata());
        });
    }
}
