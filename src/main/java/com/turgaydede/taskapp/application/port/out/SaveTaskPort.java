package com.turgaydede.taskapp.application.port.out;

import com.turgaydede.taskapp.domain.model.Task;

public interface SaveTaskPort {
    Task save(Task task);
}
