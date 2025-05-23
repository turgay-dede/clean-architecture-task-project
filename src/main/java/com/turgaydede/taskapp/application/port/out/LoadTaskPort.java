package com.turgaydede.taskapp.application.port.out;

import com.turgaydede.taskapp.domain.model.Task;

import java.util.Optional;

public interface LoadTaskPort {
    Optional<Task> findById(Long id);
}
