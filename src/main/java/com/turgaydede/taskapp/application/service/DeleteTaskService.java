package com.turgaydede.taskapp.application.service;

import com.turgaydede.taskapp.application.port.in.DeleteTaskUseCase;
import com.turgaydede.taskapp.application.port.out.DeleteTaskPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteTaskService implements DeleteTaskUseCase {

    private final DeleteTaskPort deleteTaskPort;

    @Override
    public void deleteTask(Long id) {
        deleteTaskPort.deleteById(id);
    }
}
