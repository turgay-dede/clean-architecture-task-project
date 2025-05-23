package com.turgaydede.taskapp.adapter.in.web;

import com.turgaydede.taskapp.application.port.in.*;
import com.turgaydede.taskapp.application.service.UpdateTaskCommand;
import com.turgaydede.taskapp.domain.model.Task;
import com.turgaydede.taskapp.domain.model.TaskStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final CreateTaskUseCase createTaskUseCase;
    private final GetTaskUseCase getTaskUseCase;
    private final CompleteTaskUseCase completeTaskUseCase;
    private final UpdateTaskUseCase updateTaskUseCase;
    private final AssignTaskToUserUseCase assignTaskToUserUseCase;

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody CreateTaskRequest request) {
        Task task = createTaskUseCase.createTask(request.toCommand());
        return ResponseEntity.ok(TaskResponse.from(task));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable Long id) {
        Task task = getTaskUseCase.getTaskById(id);
        return ResponseEntity.ok(TaskResponse.from(task));
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<Void> complete(@PathVariable Long id) {
        completeTaskUseCase.completeTask(id);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Long id,
            @RequestBody UpdateTaskRequest request) {

        UpdateTaskCommand command = new UpdateTaskCommand(
                id,
                request.getTitle(),
                request.getDescription(),
                TaskStatus.valueOf(request.getStatus()),
                request.getAssignee(),
                request.getDueDate()
        );

        Task updated = updateTaskUseCase.updateTask(command);
        return ResponseEntity.ok(TaskResponse.from(updated));
    }

    @PatchMapping("/{id}/assign")
    public ResponseEntity<Void> assign(
            @PathVariable Long id,
            @RequestBody AssignTaskRequest request) {

        assignTaskToUserUseCase.assign(id, request.getAssignee());
        return ResponseEntity.ok().build();
    }
}
