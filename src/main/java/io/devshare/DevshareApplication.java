package io.devshare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DevshareApplication {
	public static void main(String[] args) {
		new SpringApplication(DevshareApplication.class)
				.run(args);
	}
}
