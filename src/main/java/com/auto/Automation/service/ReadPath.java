package com.auto.Automation.service;


import java.io.InputStream;
import java.util.Properties;


public class ReadPath {

	String birthday_file_path;
	private static Properties prop = null;
	public static String dbUrl,dbUserName,dbPassword, birthdayImagesPath, birthdayQuotesPath, smtpHostName, smtpPort, subject,fromAddress, ccAddress,pattern,tableName,personName,bdyDate;
	
	public static int number_of_quotes,number_of_images;



	static {
		
		try {
			prop = new Properties();
			InputStream inputStream = ReadPath.class.getResourceAsStream("/path.properties");
			prop.load(inputStream);
			dbUrl=prop.getProperty("DatabaseUrl");
			dbUserName=prop.getProperty("DatabaseUserName");
			dbPassword=prop.getProperty("DatabasePassword");
			birthdayImagesPath=prop.getProperty("BirthdayImagePath");
			birthdayQuotesPath=prop.getProperty("BirthdayQuotesPath");
			smtpHostName=prop.getProperty("SmtpHostName");
			smtpPort=prop.getProperty("SmtpPort");
			fromAddress=prop.getProperty("FromAddress");
			ccAddress=prop.getProperty("CcAddress");
			pattern=prop.getProperty("Pattern");
			subject=prop.getProperty("Subject");
			tableName=prop.getProperty("TableName");
			personName=prop.getProperty("EmployeeColumn");
			bdyDate=prop.getProperty("DOBColumn");
			number_of_images=Integer.parseInt(prop.getProperty("Number_of_Images"));
			number_of_quotes=Integer.parseInt(prop.getProperty("Number_of_Quotes"));
		
			inputStream.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			
		}

	}

}
