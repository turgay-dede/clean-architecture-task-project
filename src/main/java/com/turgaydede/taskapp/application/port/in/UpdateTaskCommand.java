package com.turgaydede.taskapp.application.port.in;

import com.turgaydede.taskapp.domain.model.TaskStatus;

import java.time.LocalDate;

public record UpdateTaskCommand(Long id, String title, String description, String status, String assignee,
                                LocalDate dueDate) {
}
