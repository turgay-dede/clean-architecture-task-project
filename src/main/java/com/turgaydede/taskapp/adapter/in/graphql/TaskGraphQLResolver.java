package com.turgaydede.taskapp.adapter.in.graphql;

import com.turgaydede.taskapp.adapter.in.web.CreateTaskRequest;
import com.turgaydede.taskapp.adapter.in.web.TaskResponse;
import com.turgaydede.taskapp.adapter.in.web.UpdateTaskRequest;
import com.turgaydede.taskapp.application.port.in.*;
import com.turgaydede.taskapp.application.service.UpdateTaskCommand;
import com.turgaydede.taskapp.domain.model.Task;
import com.turgaydede.taskapp.domain.model.TaskStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class TaskGraphQLResolver {

    private final CreateTaskUseCase createTaskUseCase;
    private final GetTaskUseCase getTaskUseCase;
    private final CompleteTaskUseCase completeTaskUseCase;
    private final UpdateTaskUseCase updateTaskUseCase;
    private final AssignTaskToUserUseCase assignTaskToUserUseCase;
    private final DeleteTaskUseCase deleteTaskUseCase;


    @QueryMapping
    public TaskResponse getTask(@Argument Long id) {
        return TaskResponse.from(getTaskUseCase.getTaskById(id));
    }

    @MutationMapping
    public TaskResponse createTask(@Argument CreateTaskRequest input) {
        Task created = createTaskUseCase.createTask(input.toCommand());
        return TaskResponse.from(created);
    }


    @MutationMapping
    public Boolean completeTask(@Argument Long id) {
        completeTaskUseCase.completeTask(id);
        return true;
    }

    @MutationMapping
    public TaskResponse updateTask(@Argument Long id, @Argument UpdateTaskRequest input) {
        return TaskResponse.from(updateTaskUseCase.updateTask(
            new UpdateTaskCommand(
                id,
                input.getTitle(),
                input.getDescription(),
                TaskStatus.valueOf(input.getStatus()),
                input.getAssignee(),
                input.getDueDate()
            )
        ));
    }

    @MutationMapping
    public Boolean assignTask(@Argument Long id, @Argument String assignee) {
        assignTaskToUserUseCase.assign(id, assignee);
        return true;
    }

    @MutationMapping
    public Boolean deleteTask(@Argument Long id) {
        deleteTaskUseCase.deleteTask(id);
        return true;
    }
}
