package esi.classmate.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PaymentApplication {

	public static void main(String[] args) {
		DatabaseInitializer.initialize("paymentservice_db");
		SpringApplication.run(PaymentApplication.class, args);
	}

}
