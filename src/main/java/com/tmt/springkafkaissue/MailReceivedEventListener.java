package com.tmt.springkafkaissue;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class MailReceivedEventListener {


    @KafkaListener(topics = "${kafka.custom.topic.consumers.mail-received-event-topic}", groupId = "${kafka.consumer.group-id}", containerFactory = "mailReceivedEventListenerContainerFactory"
            , autoStartup = "true")
    public void onMessage(MailReceivedEvent mailReceivedEvent) {
        log.info("Mail  received from Integration to CBS : {}", mailReceivedEvent);

    }
}
