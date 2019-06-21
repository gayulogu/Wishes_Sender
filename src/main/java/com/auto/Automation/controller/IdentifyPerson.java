package com.auto.Automation.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.auto.Automation.service.IdentifyPersonService;

@Controller
public class IdentifyPerson {
	@Scheduled(cron = "0 0 0 * * ?")
	public void search() {
		IdentifyPersonService identifyPersonService = new IdentifyPersonService();
		identifyPersonService.getName();
	}
}
