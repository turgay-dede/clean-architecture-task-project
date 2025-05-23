package com.turgaydede.taskapp.application.service;

import com.turgaydede.taskapp.domain.model.TaskStatus;

import java.time.LocalDate;

public record UpdateTaskCommand(Long id, String title, String description, TaskStatus status, String assignee,
                                LocalDate dueDate) {
}
