package com.turgaydede.taskapp.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.turgaydede.taskapp.application.exception.TaskNotFoundException;
import com.turgaydede.taskapp.application.port.in.*;
import com.turgaydede.taskapp.application.service.UpdateTaskCommand;
import com.turgaydede.taskapp.domain.model.Task;
import com.turgaydede.taskapp.domain.model.TaskStatus;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @Autowired private CreateTaskUseCase createTaskUseCase;
    @Autowired private GetTaskUseCase getTaskUseCase;
    @Autowired private UpdateTaskUseCase updateTaskUseCase;
    @Autowired private CompleteTaskUseCase completeTaskUseCase;
    @Autowired private AssignTaskToUserUseCase assignTaskToUserUseCase;
    @Autowired private DeleteTaskUseCase deleteTaskUseCase;

    @TestConfiguration
    static class MockedUseCases {
        @Bean public CreateTaskUseCase createTaskUseCase() { return Mockito.mock(CreateTaskUseCase.class); }
        @Bean public GetTaskUseCase getTaskUseCase() { return Mockito.mock(GetTaskUseCase.class); }
        @Bean public CompleteTaskUseCase completeTaskUseCase() { return Mockito.mock(CompleteTaskUseCase.class); }
        @Bean public UpdateTaskUseCase updateTaskUseCase() { return Mockito.mock(UpdateTaskUseCase.class); }
        @Bean public AssignTaskToUserUseCase assignTaskToUserUseCase() { return Mockito.mock(AssignTaskToUserUseCase.class); }
        @Bean public DeleteTaskUseCase deleteTaskUseCase() { return Mockito.mock(DeleteTaskUseCase.class); }
    }

    @Test
    void shouldCreateTask() throws Exception {
        Task mockTask = Task.builder()
                .id(1L)
                .title("Create Test")
                .description("Desc")
                .status(TaskStatus.OPEN)
                .assignee("turgay")
                .dueDate(LocalDate.of(2025, 6, 10))
                .build();

        Mockito.when(createTaskUseCase.createTask(any())).thenReturn(mockTask);

        CreateTaskRequest request = new CreateTaskRequest();
        request.setTitle("Create Test");
        request.setDescription("Desc");
        request.setAssignee("turgay");
        request.setDueDate(LocalDate.of(2025, 6, 10));

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Create Test"));
    }

    @Test
    void shouldGetTask() throws Exception {
        Task task = Task.builder()
                .id(99L)
                .title("Mocked Task")
                .description("From Get")
                .status(TaskStatus.OPEN)
                .assignee("turgay")
                .dueDate(LocalDate.now().plusDays(5))
                .build();

        Mockito.when(getTaskUseCase.getTaskById(99L)).thenReturn(task);

        mockMvc.perform(get("/tasks/99"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Mocked Task"));
    }

    @Test
    void shouldUpdateTask() throws Exception {
        Task updated = Task.builder()
                .id(7L)
                .title("Updated")
                .description("Updated Desc")
                .status(TaskStatus.DONE)
                .assignee("kutay")
                .dueDate(LocalDate.of(2025, 12, 1))
                .build();

        Mockito.when(updateTaskUseCase.updateTask(any())).thenReturn(updated);

        UpdateTaskRequest request = new UpdateTaskRequest();
        request.setTitle("Updated");
        request.setDescription("Updated Desc");
        request.setStatus("DONE");
        request.setAssignee("kutay");
        request.setDueDate(LocalDate.of(2025, 12, 1));

        mockMvc.perform(put("/tasks/7")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("DONE"));
    }

    @Test
    void shouldCompleteTask() throws Exception {
        mockMvc.perform(patch("/tasks/5/complete"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldAssignTask() throws Exception {
        AssignTaskRequest request = new AssignTaskRequest();
        request.setAssignee("kutay");

        mockMvc.perform(patch("/tasks/3/assign")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteTask() throws Exception {
        mockMvc.perform(delete("/tasks/10"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturnNotFoundForUnknownTask() throws Exception {
        Mockito.when(getTaskUseCase.getTaskById(999L))
                .thenThrow(new TaskNotFoundException(999L));

        mockMvc.perform(get("/tasks/999"))
                .andExpect(status().isNotFound());
    }

}
