package com.turgaydede.taskapp.domain.model;

import com.turgaydede.taskapp.application.exception.InvalidTaskException;
import com.turgaydede.taskapp.application.exception.InvalidTaskStateException;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private String assignee;
    private LocalDate dueDate;

    public static Task create(String title, String description, String assignee, LocalDate dueDate) {
        if (title == null || title.trim().isEmpty()) {
            throw new InvalidTaskException("Task title must not be empty.");
        }
        if (dueDate.isBefore(LocalDate.now())) {
            throw new InvalidTaskException("Due date cannot be in the past.");
        }
        return new Task(null, title, description, TaskStatus.OPEN, assignee, dueDate);
    }

    public void markAsDone() {
        if (status == TaskStatus.DONE) {
            return;
        }
        if (status != TaskStatus.OPEN && status != TaskStatus.IN_PROGRESS) {
            throw new InvalidTaskStateException("Only OPEN or IN_PROGRESS tasks can be marked as DONE.");
        }
        this.status = TaskStatus.DONE;
    }
}
