package com.turgaydede.taskapp.application.service;

import java.time.LocalDate;

public record CreateTaskCommand(String title, String description, String assignee, LocalDate dueDate) {
}
