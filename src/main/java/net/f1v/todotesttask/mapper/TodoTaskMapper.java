package net.f1v.todotesttask.mapper;

import net.f1v.todotesttask.dto.TodoTaskRequest;
import net.f1v.todotesttask.dto.TodoTaskResponse;
import net.f1v.todotesttask.entity.TodoTask;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface TodoTaskMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    TodoTask toTodoTask(TodoTaskRequest todoTaskRequest);

    TodoTaskResponse toTodoTaskResponse(TodoTask todoTask);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateTodoTask(TodoTaskRequest todoTaskRequest, @MappingTarget TodoTask todoTask);
}
