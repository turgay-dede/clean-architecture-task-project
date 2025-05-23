package com.turgaydede.taskapp.application.port.in;

import com.turgaydede.taskapp.domain.model.Task;

public interface GetTaskUseCase {
    Task getTaskById(Long id);
}
