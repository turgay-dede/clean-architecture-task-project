package com.turgaydede.taskapp.application.service;

import com.turgaydede.taskapp.application.port.in.CreateTaskUseCase;
import com.turgaydede.taskapp.application.port.out.SaveTaskPort;
import com.turgaydede.taskapp.domain.model.Task;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateTaskService implements CreateTaskUseCase {

    private final SaveTaskPort saveTaskPort;

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
