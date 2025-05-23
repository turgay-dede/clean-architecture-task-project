package com.turgaydede.taskapp.adapter.out.persistence;

import com.turgaydede.taskapp.domain.model.Task;
import com.turgaydede.taskapp.domain.model.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String status;
    private String assignee;
    private LocalDate dueDate;

    public static TaskEntity fromDomain(Task task) {
        TaskEntity entity = new TaskEntity();
        entity.id = task.getId();
        entity.title = task.getTitle();
        entity.description = task.getDescription();
        entity.status = task.getStatus().name();
        entity.assignee = task.getAssignee();
        entity.dueDate = task.getDueDate();
        return entity;
    }

    public Task toDomain() {
        return new Task(
                id,
                title,
                description,
                TaskStatus.valueOf(status),
                assignee,
                dueDate
        );
    }
}
