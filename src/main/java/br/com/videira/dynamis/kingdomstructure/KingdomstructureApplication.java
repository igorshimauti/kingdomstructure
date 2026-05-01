package br.com.videira.dynamis.kingdomstructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class KingdomstructureApplication {

	static void main(String[] args) {
		SpringApplication.run(KingdomstructureApplication.class, args);
	}
}