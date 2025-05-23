package com.turgaydede.taskapp.application.service;

import com.turgaydede.taskapp.application.exception.TaskNotFoundException;
import com.turgaydede.taskapp.application.port.in.CompleteTaskUseCase;
import com.turgaydede.taskapp.application.port.out.LoadTaskPort;
import com.turgaydede.taskapp.application.port.out.NotifyAssigneePort;
import com.turgaydede.taskapp.application.port.out.SaveTaskPort;
import com.turgaydede.taskapp.domain.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompleteTaskService implements CompleteTaskUseCase {

    private final LoadTaskPort loadTaskPort;
    private final SaveTaskPort saveTaskPort;
    private final NotifyAssigneePort notifyAssigneePort;

    @Override
    public void completeTask(Long taskId) {
        Task task = loadTaskPort.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));

        task.markAsDone();

        saveTaskPort.save(task);

        notifyAssigneePort.sendNotification(task.getAssignee(), "Your task is completed.");

    }
}