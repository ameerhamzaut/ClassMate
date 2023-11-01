package esi.classmate.timetable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TimetableApplication {

	public static void main(String[] args) {
		DatabaseInitializer.initialize("timetableservice_db");
		SpringApplication.run(TimetableApplication.class, args);
	}

}
