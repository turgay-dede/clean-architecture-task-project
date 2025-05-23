package com.turgaydede.taskapp.adapter.in.web;

import com.turgaydede.taskapp.domain.model.Task;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private String status;
    private String assignee;
    private LocalDate dueDate;

    public static TaskResponse from(Task task) {
        TaskResponse response = new TaskResponse();
        response.id = task.getId();
        response.title = task.getTitle();
        response.description = task.getDescription();
        response.status = task.getStatus().name();
        response.assignee = task.getAssignee();
        response.dueDate = task.getDueDate();
        return response;
    }
}
