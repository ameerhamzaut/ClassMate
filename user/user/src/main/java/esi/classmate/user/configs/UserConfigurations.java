package esi.classmate.user.configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class UserConfigurations {

    @Bean
    public NewTopic enrollmentTopicCreation() {
        return TopicBuilder.name("enrollmentTopic")
                .build();
    }
}
