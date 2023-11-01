package esi.classmate.grade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GradeApplication {

	public static void main(String[] args) {
		DatabaseInitializer.initialize("gradeservice_db");
		SpringApplication.run(GradeApplication.class, args);
	}

}
