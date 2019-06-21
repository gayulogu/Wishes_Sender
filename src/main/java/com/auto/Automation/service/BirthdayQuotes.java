package com.auto.Automation.service;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.stereotype.Service;

@Service
public class BirthdayQuotes {
	String quote;
	int max=ReadPath.number_of_quotes,min=1,x;
	String index;
	InputStream inputstream;
	String birthdayQuote() throws IOException
	{
		  x = (int) ((Math.random()*((max-min)+1))+min);
			index=String.valueOf(x<=max?x:1);
			
		  try {
				
			  	inputstream=this.getClass().getResourceAsStream(ReadPath.birthdayQuotesPath);
				Properties props = new Properties();
				props.load(inputstream);
				quote = props.getProperty(index);		
				
			   } catch (Exception e) {
				
				   e.printStackTrace();
				
			}finally {
				
				inputstream.close();
			}
		  
		return quote;
	}
	
}
