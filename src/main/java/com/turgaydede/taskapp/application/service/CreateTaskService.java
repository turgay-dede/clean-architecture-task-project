package com.turgaydede.taskapp.application.service;

import com.turgaydede.taskapp.application.port.in.CreateTaskUseCase;
import com.turgaydede.taskapp.application.port.out.SaveTaskPort;
import com.turgaydede.taskapp.domain.model.Task;
import org.springframework.stereotype.Service;

@Service
public class CreateTaskService implements CreateTaskUseCase {

    private final SaveTaskPort saveTaskPort;

    public CreateTaskService(SaveTaskPort saveTaskPort) {
        this.saveTaskPort = saveTaskPort;
    }

    @Override
    public Task createTask(CreateTaskCommand command) {
        Task task = Task.create(
            command.title(),
            command.description(),
            command.assignee(),
            command.dueDate()
        );
        return saveTaskPort.save(task);
    }
}
