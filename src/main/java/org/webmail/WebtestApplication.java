package org.webmail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableAutoConfiguration
public class WebtestApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebtestApplication.class, args);
	}
}
