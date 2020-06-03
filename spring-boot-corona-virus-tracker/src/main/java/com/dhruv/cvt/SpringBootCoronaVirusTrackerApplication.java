package com.dhruv.cvt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootCoronaVirusTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCoronaVirusTrackerApplication.class, args);
	}

}
