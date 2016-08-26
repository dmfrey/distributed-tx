package io.pivotal.dmfrey.distributed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DistributedTxApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistributedTxApplication.class, args);
	}
}
