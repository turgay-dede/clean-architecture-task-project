package com.turgaydede.taskapp.adapter.in.web;

import com.turgaydede.taskapp.application.port.in.CompleteTaskUseCase;
import com.turgaydede.taskapp.application.port.in.CreateTaskUseCase;
import com.turgaydede.taskapp.application.port.in.GetTaskUseCase;
import com.turgaydede.taskapp.domain.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class CreateTaskController {

    private final CreateTaskUseCase createTaskUseCase;
    private final GetTaskUseCase getTaskUseCase;
    private final CompleteTaskUseCase completeTaskUseCase;

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

}
