package com.turgaydede.taskapp.application.port.out;

public interface NotifyAssigneePort {
    void sendNotification(String assigneeEmail, String message);
}
