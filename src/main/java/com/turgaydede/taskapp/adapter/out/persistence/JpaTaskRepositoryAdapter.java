package com.turgaydede.taskapp.adapter.out.persistence;

import com.turgaydede.taskapp.application.port.out.LoadTaskPort;
import com.turgaydede.taskapp.application.port.out.SaveTaskPort;
import com.turgaydede.taskapp.domain.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaTaskRepositoryAdapter implements SaveTaskPort, LoadTaskPort {

    private final SpringDataTaskRepository repository;

    @Override
    public Task save(Task task) {
        TaskEntity entity = TaskEntity.fromDomain(task);
        TaskEntity saved = repository.save(entity);
        return saved.toDomain();
    }

    @Override
    public Optional<Task> findById(Long id) {
        return repository.findById(id).map(TaskEntity::toDomain);
    }
}
