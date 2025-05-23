package com.turgaydede.taskapp.adapter.in.web;

import com.turgaydede.taskapp.application.port.in.CreateTaskCommand;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class CreateTaskRequest {
    private String title;
    private String description;
    private String assignee;
    private LocalDate dueDate;

    public CreateTaskCommand toCommand() {
        return new CreateTaskCommand(title, description, assignee, dueDate);
    }
}
