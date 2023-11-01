package esi.classmate.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NotificationApplication {

	public static void main(String[] args) {
		DatabaseInitializer.initialize("notificationservice_db");
		SpringApplication.run(NotificationApplication.class, args);
	}

}
