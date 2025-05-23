package com.turgaydede.taskapp.adapter.in.web;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskRequest {
    private String title;
    private String description;
    private String status;
    private String assignee;
    private LocalDate dueDate;
}
