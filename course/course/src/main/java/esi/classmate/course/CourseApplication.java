package esi.classmate.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CourseApplication {

	public static void main(String[] args) {
		DatabaseInitializer.initialize("courseservice_db");
		SpringApplication.run(CourseApplication.class, args);
	}

}
