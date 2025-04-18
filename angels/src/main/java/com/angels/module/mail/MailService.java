package com.angels.module.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {
	
	@Autowired
	private JavaMailSender javaMailSender;

	
//	회원가입 축하 메일
    public void sendMailWelcome() throws Exception{

    	
    	MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    	MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
    	mimeMessageHelper.setTo("eunjuni1221@gmail.com"); 
    	mimeMessageHelper.setSubject("hi");
    	mimeMessageHelper.setText("gdgd", true); 
    	javaMailSender.send(mimeMessage);
    	
    }
}
