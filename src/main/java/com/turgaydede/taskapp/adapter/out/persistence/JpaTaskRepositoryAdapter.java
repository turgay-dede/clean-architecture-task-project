package com.turgaydede.taskapp.adapter.out.persistence;

import com.turgaydede.taskapp.application.port.out.SaveTaskPort;
import com.turgaydede.taskapp.domain.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JpaTaskRepositoryAdapter implements SaveTaskPort {

    private final SpringDataTaskRepository repository;

    @Override
    public Task save(Task task) {
        TaskEntity entity = TaskEntity.fromDomain(task);
        TaskEntity saved = repository.save(entity);
        return saved.toDomain();
    }
}
