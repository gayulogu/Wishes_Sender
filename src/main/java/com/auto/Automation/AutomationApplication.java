package com.auto.Automation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.auto.Automation.controller.IdentifyPerson;
import com.auto.Automation.service.IdentifyPersonService;

@SpringBootApplication
@EnableScheduling
public class AutomationApplication {


	public static void main(String[] args) {
		SpringApplication.run(AutomationApplication.class, args);
		
	}

}

