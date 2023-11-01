package esi.classmate.grade.configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

public class GradeConfigurations {
    @Bean
    public NewTopic paymentTopicCreation() {
        return TopicBuilder.name("enrollmentTopic")
                .build();
    }

}
