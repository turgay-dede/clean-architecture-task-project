package com.turgaydede.taskapp.application.port.in;

public interface AssignTaskToUserUseCase {
    void assign(Long taskId, String assignee);
}
