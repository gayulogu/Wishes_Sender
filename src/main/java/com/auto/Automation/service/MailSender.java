package com.auto.Automation.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.auto.Automation.model.MailSenderInfo;

public class MailSender extends Thread {

	public static String name;
	
	public MailSender() {}

	public MailSender(MailSenderInfo mailSenderInfo) {
		super();
	}

	public boolean sendBirthdayMail(String name, String email) {
		new MailSender();
		MailSender.name = name;

		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setSubject(ReadPath.subject + " " + MailSender.name);
		mailInfo.setContent("Birthday Message");
		mailInfo.setFromAddress(ReadPath.fromAddress);
		mailInfo.setMailServerHost(ReadPath.smtpHostName);
		mailInfo.setMailServerPort(ReadPath.smtpPort);
		mailInfo.setToAddress(email);
		mailInfo.setCcAddress(ReadPath.ccAddress);

		if(sendBirthdayHtmlMail(mailInfo))
			return true;
		else
			return false;
	}

	/**
	 * Reads the recipients and split it on the basis of , add the recipients to the
	 * Address array
	 * 
	 * @param address
	 * @return
	 */
	

	public static boolean sendBirthdayHtmlMail(MailSenderInfo mailInfo) {
		Properties pro = mailInfo.getProperties();
		Session sendMailSession = Session.getDefaultInstance(pro);
		MimeMultipart multipart = new MimeMultipart("related");
		try {
			
			Message mailMessage = new MimeMessage(sendMailSession);
			InternetAddress from = new InternetAddress(mailInfo.getFromAddress(),"WebMaster");

			InternetAddress addrTo = new InternetAddress(mailInfo.getToAddress());
			
			mailMessage.setFrom(from);
			mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mailInfo.getToAddress()));
			mailMessage.setRecipient(Message.RecipientType.CC, new InternetAddress(mailInfo.getCcAddress()));
			mailMessage.setSubject(mailInfo.getSubject());
			mailMessage.setSentDate(new Date());
			BodyPart messageBodyPart=new MimeBodyPart();
			InputStream url = null;

			try {
				BirthdayQuotes birthdayQuote = new BirthdayQuotes();
				
				String htmlText = "<H1>" + messageFormatBirthday(MailSender.name, birthdayQuote.birthdayQuote());
				
				messageBodyPart.setContent(htmlText, "text/html");
				multipart.addBodyPart(messageBodyPart);// add it to Mimemultipart

				
				
				// second part (adding image to the frame)
				messageBodyPart = new MimeBodyPart();
				url = MailSender.class.getResourceAsStream("/"+
						ReadPath.birthdayImagesPath+"/"+selectRandomBirthdayImage()+".jpg");
				messageBodyPart.setDataHandler(new DataHandler(new InputStreamDataSource("text/html", url)));
				messageBodyPart.setHeader("Content-ID", "<image>");
				
				
				
				
				// put everything together
				multipart.addBodyPart(messageBodyPart);
				mailMessage.setContent(multipart);
				
				
				
				//send mail
				Transport.send(mailMessage);
				 
								
				return true;
				
			} catch (MessagingException e) {
				
				e.printStackTrace();	
				
			} catch (IOException e) {
			
				e.printStackTrace();
				
			}
		} catch (MessagingException ex) {
			
			ex.printStackTrace();
			
		} catch (UnsupportedEncodingException e1) {
			
			e1.printStackTrace();
		}
		return false;
	}
	
	private static String messageFormatBirthday(String personname, String quote) {

		StringBuilder strHTML = new StringBuilder();
		strHTML.append("<html>");
		
		strHTML.append("<head>");
		strHTML.append("<title>Our Warm Wishes</title>");
		strHTML.append("</head>");
		
		strHTML.append("<body>");
		strHTML.append(
				"<table style='box-shadow:�10px�10px�10px�5px�rgba(255,�255,�255,�0.4),�0px�4px�20px�rgba(0,�0,�0,�0.92)' cellspacing='0' border-collapse= collapse border-spacing= '0' align='center' style='width:75%'>");
		strHTML.append("<tr width=75% style='background:#FFB74D'>");
		strHTML.append(
				"<td width=500 height=400 style='background:#FFB74D'><img src='cid:image' width=500 height=400></td> ");
		strHTML.append(
				"<td  cellpadding='30' style='font-family: Verdana, Arial, Helvetica, sans-serif; background: linear-gradient(to right, #005aa7, #fffde4);' valign='middle' align='center''width=300 height=400>");
		strHTML.append(
				"<b><i><p><font size='7' face='Colonna MT' color='#E91E63' > " + personname + "	!</font></p></i></b>");
		strHTML.append("<p ><font size='5' face='Pristina' color='#1A237E'>" + quote + "</font></p></td></tr>");
		strHTML.append("</table>");
		strHTML.append("</body>");
		
		strHTML.append("</html>");
		strHTML.append("\n");
		return strHTML.toString();

	}

	static String selectRandomBirthdayImage() {
		int max = ReadPath.number_of_images, min = 1;
		int x = (int) ((Math.random() * ((max - min) + 1)) + min);
		return String.valueOf(x<=max?x:1);
	}

}
