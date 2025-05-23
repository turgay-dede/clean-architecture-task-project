package com.turgaydede.taskapp.adapter.out.mail;

import com.turgaydede.taskapp.application.port.out.NotifyAssigneePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailNotificationAdapter implements NotifyAssigneePort {

    private final MailClient mailClient;

    @Override
    public void sendNotification(String to, String message) {
        mailClient.send(to, "Task Completed", message);
    }
}
