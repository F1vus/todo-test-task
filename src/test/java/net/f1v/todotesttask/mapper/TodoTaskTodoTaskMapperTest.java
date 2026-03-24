package net.f1v.todotesttask.mapper;

import net.f1v.todotesttask.dto.TodoTaskRequest;
import net.f1v.todotesttask.dto.TodoTaskResponse;
import net.f1v.todotesttask.entity.TodoStatus;
import net.f1v.todotesttask.entity.TodoTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class TodoTaskTodoTaskMapperTest {
    private TodoTaskMapper todoTaskMapper;

    @BeforeEach
    void setUp() {
        todoTaskMapper = Mappers.getMapper(TodoTaskMapper.class);
    }

    @Test
    void toTodoTask_shouldConvertTodoTaskRequestToTodoTask() {
        TodoTaskRequest todoTaskRequest = new TodoTaskRequest("Title", null, TodoStatus.NEW);

        TodoTask todoTask = todoTaskMapper.toTodoTask(todoTaskRequest);

        assertThat(todoTask.getId())
                .isNull();
        assertThat(todoTask)
                .hasFieldOrPropertyWithValue("title", todoTaskRequest.title())
                .hasFieldOrPropertyWithValue("description", todoTaskRequest.description())
                .hasFieldOrPropertyWithValue("status", todoTaskRequest.status());
        assertThat(todoTask.getCreatedAt())
                .isNotNull();
    }

    @Test
    void toTodoTaskResponse_shouldConvertTodoTaskToTodoTaskResponse() {
        TodoTask todoTask = new TodoTask(1L, "Title", "Description", TodoStatus.NEW, LocalDateTime.now());

        TodoTaskResponse todoTaskResponse = todoTaskMapper.toTodoTaskResponse(todoTask);

        assertThat(todoTaskResponse)
                .hasFieldOrPropertyWithValue("id", todoTask.getId())
                .hasFieldOrPropertyWithValue("title", todoTask.getTitle())
                .hasFieldOrPropertyWithValue("description", todoTask.getDescription())
                .hasFieldOrPropertyWithValue("status", todoTask.getStatus())
                .hasFieldOrPropertyWithValue("createdAt", todoTask.getCreatedAt());
    }

    @Test
    void updateTodoTask_shouldSetFieldsFromTodoTaskRequestToTodoTask() {
        TodoTask todoTask = new TodoTask(1L, "Title", "Description", TodoStatus.NEW, LocalDateTime.now());
        TodoTaskRequest todoTaskRequest = new TodoTaskRequest("New Title", "Description", TodoStatus.IN_PROGRESS);

        todoTaskMapper.updateTodoTask(todoTaskRequest, todoTask);

        assertThat(todoTask)
                .hasFieldOrPropertyWithValue("title", todoTaskRequest.title())
                .hasFieldOrPropertyWithValue("description", todoTaskRequest.description())
                .hasFieldOrPropertyWithValue("status", todoTaskRequest.status());
    }
}
