package net.f1v.todotesttask.service;

import net.f1v.todotesttask.dto.TodoTaskRequest;
import net.f1v.todotesttask.dto.TodoTaskResponse;
import net.f1v.todotesttask.entity.TodoTask;
import net.f1v.todotesttask.exception.TodoTaskNotFoundException;
import net.f1v.todotesttask.mapper.TodoTaskMapper;
import net.f1v.todotesttask.repository.TodoTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class TodoTaskService {
    private final TodoTaskMapper todoTaskMapper;
    private final TodoTaskRepository todoTaskRepository;

    @Transactional
    public TodoTaskResponse createTodoTask(TodoTaskRequest todoTaskRequest) {
        TodoTask todoTask = this.todoTaskMapper.toTodoTask(todoTaskRequest);
        this.todoTaskRepository.save(todoTask);
        return this.todoTaskMapper.toTodoTaskResponse(todoTask);
    }

    @Transactional(readOnly = true)
    public Page<TodoTaskResponse> getTodoTasks(Pageable pageable) {
        return this.todoTaskRepository.findAll(pageable)
                .map(this.todoTaskMapper::toTodoTaskResponse);
    }

    @Transactional(readOnly = true)
    public TodoTaskResponse getTodoTask(Long id) {
        TodoTask todoTask = this.todoTaskRepository.findById(id)
                .orElseThrow(TodoTaskNotFoundException::new);
        return this.todoTaskMapper.toTodoTaskResponse(todoTask);
    }

    @Transactional
    public TodoTaskResponse updateTodoTask(Long id, TodoTaskRequest todoTaskRequest) {
        TodoTask todoTask = this.todoTaskRepository.findById(id)
                .orElseThrow(TodoTaskNotFoundException::new);
        this.todoTaskMapper.updateTodoTask(todoTaskRequest, todoTask);
        this.todoTaskRepository.save(todoTask);
        return this.todoTaskMapper.toTodoTaskResponse(todoTask);
    }

    @Transactional
    public void deleteTodoTask(Long id) {
        this.todoTaskRepository.deleteById(id);
    }
}