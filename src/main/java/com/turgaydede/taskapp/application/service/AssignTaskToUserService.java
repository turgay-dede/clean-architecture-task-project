package com.turgaydede.taskapp.application.service;

import com.turgaydede.taskapp.application.port.in.AssignTaskToUserUseCase;
import com.turgaydede.taskapp.application.port.out.LoadTaskPort;
import com.turgaydede.taskapp.application.port.out.SaveTaskPort;
import com.turgaydede.taskapp.domain.exception.TaskNotFoundException;
import com.turgaydede.taskapp.domain.model.Task;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AssignTaskToUserService implements AssignTaskToUserUseCase {

    private final LoadTaskPort loadTaskPort;
    private final SaveTaskPort saveTaskPort;

    @Override
    public void assign(Long taskId, String assignee) {
        Task task = loadTaskPort.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));

        task.assignTo(assignee);
        saveTaskPort.save(task);
    }
}
