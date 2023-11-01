package esi.classmate.payment.configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

public class PaymentConfigurations {

    @Bean
    public NewTopic paymentTopicCreation() {
        return TopicBuilder.name("enrollmentTopic")
                .build();
    }

}
