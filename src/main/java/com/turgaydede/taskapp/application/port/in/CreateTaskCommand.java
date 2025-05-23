package com.turgaydede.taskapp.application.port.in;

import java.time.LocalDate;

public record CreateTaskCommand(String title, String description, String assignee, LocalDate dueDate) {
}
