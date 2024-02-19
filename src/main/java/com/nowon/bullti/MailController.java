package com.nowon.bullti;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nowon.bullti.service.MailSendService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MailController {

	private final MailSendService mailSendService;
	
	@PostMapping("/email-auth")
	public ResponseEntity<?> mailSend(@RequestBody String toEmail) {
		log.debug("이메일"+toEmail);
		//인증메일을 보내자
		mailSendService.sendAuthMail(toEmail);
		return ResponseEntity.ok().body("발송완료");
	}
}
