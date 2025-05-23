package com.turgaydede.taskapp.adapter.out.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MailClient {

    public void send(String to, String subject, String body) {
        log.info("Sending mail to: {}\nSubject: {}\nBody: {}", to, subject, body);
    }
}
