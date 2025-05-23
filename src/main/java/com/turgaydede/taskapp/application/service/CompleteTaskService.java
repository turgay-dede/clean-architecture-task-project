package com.turgaydede.taskapp.application.service;

import com.turgaydede.taskapp.application.exception.TaskNotFoundException;
import com.turgaydede.taskapp.application.port.in.CompleteTaskUseCase;
import com.turgaydede.taskapp.application.port.out.LoadTaskPort;
import com.turgaydede.taskapp.application.port.out.SaveTaskPort;
import com.turgaydede.taskapp.domain.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompleteTaskService implements CompleteTaskUseCase {

    private final LoadTaskPort loadTaskPort;
    private final SaveTaskPort saveTaskPort;

    @Override
    public void completeTask(Long taskId) {
        Task task = loadTaskPort.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));

        task.markAsDone();

        saveTaskPort.save(task);
    }
}