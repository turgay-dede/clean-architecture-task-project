package com.turgaydede.taskapp.config;

import com.turgaydede.taskapp.application.port.in.*;
import com.turgaydede.taskapp.application.port.out.DeleteTaskPort;
import com.turgaydede.taskapp.application.port.out.LoadTaskPort;
import com.turgaydede.taskapp.application.port.out.NotifyAssigneePort;
import com.turgaydede.taskapp.application.port.out.SaveTaskPort;
import com.turgaydede.taskapp.application.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskConfiguration {

    @Bean
    public CompleteTaskUseCase completeTaskUseCase(
            LoadTaskPort loadTaskPort,
            SaveTaskPort saveTaskPort,
            NotifyAssigneePort notifyAssigneePort
    ) {
        return new CompleteTaskService(loadTaskPort, saveTaskPort, notifyAssigneePort);
    }

    @Bean
    public CreateTaskUseCase createTaskUseCase(SaveTaskPort saveTaskPort) {
        return new CreateTaskService(saveTaskPort);
    }

    @Bean
    public GetTaskUseCase getTaskUseCase(LoadTaskPort loadTaskPort) {
        return new GetTaskService(loadTaskPort);
    }

    @Bean
    public UpdateTaskUseCase updateTaskUseCase(
            LoadTaskPort loadTaskPort,
            SaveTaskPort saveTaskPort
    ) {
        return new UpdateTaskService(loadTaskPort, saveTaskPort);
    }

    @Bean
    public AssignTaskToUserUseCase assignTaskToUserUseCase(
            LoadTaskPort loadTaskPort,
            SaveTaskPort saveTaskPort
    ) {
        return new AssignTaskToUserService(loadTaskPort, saveTaskPort);
    }

    @Bean
    public DeleteTaskUseCase deleteTaskUseCase(DeleteTaskPort deleteTaskPort) {
        return new DeleteTaskService(deleteTaskPort);
    }
}

