package com.example.demo.model.service;

import javax.mail.internet.MimeMessage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.demo.model.error.SendEmailException;

@Service
public class SendMailService {                                       //Servicio de envio de correos

	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendEmail(String from, String to, String subject,String body) throws SendEmailException {
		try {
		 MimeMessage mailMessage = javaMailSender.createMimeMessage();
		 MimeMessageHelper helper;
		 		 
         mailMessage.setSubject(subject);
         
         helper = new MimeMessageHelper(mailMessage, true);                    //Metodo que construye cuerpo de email y envio
         helper.setFrom(from);
         helper.setTo(to);
         helper.setText(body, true);
                
		 javaMailSender.send(mailMessage);
		 
	 } catch (Exception ex) {
		 ex.printStackTrace();
         throw new SendEmailException();
     }
	}
}
