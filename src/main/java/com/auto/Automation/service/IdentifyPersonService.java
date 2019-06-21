package com.auto.Automation.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.auto.Automation.dao.BirthdayDetailsDao;

@Service
public class IdentifyPersonService {

	BirthdayDetailsDao bRepository = new BirthdayDetailsDao();

	MailSender mailSender = new MailSender();

	public void getName() {
		ResultSet rs;
		try {
			rs = bRepository.getResultSet();
			if (rs == null) {
				return;
			}
			
			Map<String,String> map = new HashMap<String,String>();
			
			while (rs.next()) {
				
				map.put(rs.getString(1), rs.getString(2));
			}
		
			if(map.size()>0) {
				System.out.println(map.size());			
				for(Map.Entry<String, String> dataSet:map.entrySet()) {
					if(mailSender.sendBirthdayMail(dataSet.getKey(), dataSet.getValue())) {
						System.out.println(dataSet.getKey() +" is celebrating birthday today");
					}
				}
			}
			else
				
				System.out.println("None have birthday today");
					
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
	}
}
