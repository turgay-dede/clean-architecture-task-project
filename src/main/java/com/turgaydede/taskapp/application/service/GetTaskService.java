package com.turgaydede.taskapp.application.service;

import com.turgaydede.taskapp.application.exception.TaskNotFoundException;
import com.turgaydede.taskapp.application.port.in.GetTaskUseCase;
import com.turgaydede.taskapp.application.port.out.LoadTaskPort;
import com.turgaydede.taskapp.domain.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetTaskService implements GetTaskUseCase {

    private final LoadTaskPort loadTaskPort;

    @Override
    public Task getTaskById(Long id) {
        return loadTaskPort.findById(id)
            .orElseThrow(() -> new TaskNotFoundException(id));
    }
}
