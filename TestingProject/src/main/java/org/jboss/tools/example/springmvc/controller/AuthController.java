package org.jboss.tools.example.springmvc.controller;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Future;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;

@Service
@EnableAsync
public class AuthController {
		Properties props;
		
	private SecureRandom random = new SecureRandom();
	
	public String nextSessionId() {
		String s = new BigInteger(130, random).toString(32);
		System.out.println(s.substring(0, 6));
	    return s.substring(0, 6).toUpperCase();
	  }
	public AuthController() {
		props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
	}
	
	@Async
	public Future<Boolean> sendEmail(String to,String code) {
		System.out.println("Vou mandar o email");
		System.out.println(code);
		String msg =  "Insira este Codigo de seguranca no site: " + code;
		Session session = Session.getInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("portaldoutente.ml","loladaxisdee");
				}
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("robalogordinho@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSubject("Testing Subject");
			message.setText(msg);
			Transport.send(message);

			return new AsyncResult<Boolean>(true);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public String sendSms(String to) throws TwilioRestException {
		
		final String ACCOUNT_SID = "ACc111d083b29757594f7f6c9357d4b132"; 
		final String AUTH_TOKEN = "67f16162b110b7ba1edd9eaf886b909b"; 
		
		TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN); 
		
		String code = nextSessionId();
		
	   // Build the parameters 
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("To", "+351"+to)); 
		params.add(new BasicNameValuePair("From", "+17064034366")); 
		params.add(new BasicNameValuePair("Body", "Obrigado por se registar no novo Portal do Utente. Por favor insira o seguinte codigo no site: \n" + code)); 
		
		MessageFactory messageFactory = client.getAccount().getMessageFactory(); 
		com.twilio.sdk.resource.instance.Message message = messageFactory.create(params); 
		System.out.println(message.getSid());
		
		return code;
	}
	
	public boolean verifyEmail(String email){
		boolean res = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      res = false;
		   }
		   return res;
	}
}