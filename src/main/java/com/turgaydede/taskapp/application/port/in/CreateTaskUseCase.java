package com.turgaydede.taskapp.application.port.in;

import com.turgaydede.taskapp.application.service.CreateTaskCommand;
import com.turgaydede.taskapp.domain.model.Task;

public interface CreateTaskUseCase {
    Task createTask(CreateTaskCommand command);
}
