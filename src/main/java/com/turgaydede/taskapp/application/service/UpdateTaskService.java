package com.turgaydede.taskapp.application.service;

import com.turgaydede.taskapp.application.port.in.UpdateTaskCommand;
import com.turgaydede.taskapp.application.port.in.UpdateTaskUseCase;
import com.turgaydede.taskapp.application.port.out.LoadTaskPort;
import com.turgaydede.taskapp.application.port.out.SaveTaskPort;
import com.turgaydede.taskapp.domain.exception.TaskNotFoundException;
import com.turgaydede.taskapp.domain.model.Task;
import com.turgaydede.taskapp.domain.model.TaskStatus;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateTaskService implements UpdateTaskUseCase {

    private final LoadTaskPort loadTaskPort;
    private final SaveTaskPort saveTaskPort;

    @Override
    public Task updateTask(UpdateTaskCommand command) {
        Task task = loadTaskPort.findById(command.id())
            .orElseThrow(() -> new TaskNotFoundException(command.id()));

        task.updateDetails(
            command.title(),
            command.description(),
            TaskStatus.valueOf(command.status()),
            command.assignee(),
            command.dueDate()
        );

        return saveTaskPort.save(task);
    }
}
