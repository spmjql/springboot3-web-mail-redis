package com.nowon.bullti.service.impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.nowon.bullti.service.MailSendService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MailSendProcess implements MailSendService {

	private final JavaMailSender sender;
	
	@Value("${spring.mail.username}")
	private String from;

	@Override
	public void sendAuthMail(String toEmail) {
		//메일을 전송
		sender.send( createSimpleMessage(toEmail) );
	}

	//심플메일메세지객체 세팅
	private SimpleMailMessage createSimpleMessage(String toEmail) {
		SimpleMailMessage simpleMessage=new SimpleMailMessage();
		String code = createCode();
		simpleMessage.setFrom(from); // 보내는 사람
		simpleMessage.setTo(toEmail); // 받는사람
		simpleMessage.setSubject("OO회사 이메일 인증번호입니다");
		simpleMessage.setText("인증번호: "+code);
		
		System.out.println(simpleMessage.toString()); 
		return simpleMessage;
	}

	//인증번호 생성
	private String createCode() {
		return new Random().ints(48, 58).limit(6)
				.collect(
						StringBuilder::new , 
						StringBuilder::appendCodePoint, 
						StringBuilder::append)
				.toString();
	}
	
	
}
