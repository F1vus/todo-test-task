package net.f1v.todotesttask.controller;

import net.f1v.todotesttask.dto.TodoTaskRequest;
import net.f1v.todotesttask.dto.TodoTaskResponse;
import net.f1v.todotesttask.service.TodoTaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.boot.webmvc.error.ErrorAttributes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping(path = "/api/v1/tasks")
@Tag(name = "Todo Tasks", description = "Todo tasks management")
@RequiredArgsConstructor
public class TodoTaskController {
    private final TodoTaskService todoTaskService;


    @PostMapping
    @Operation(summary = "Create a new todo task", description = "Creates and saves a new todo task to the database")
    @ApiResponse(responseCode = "201", description = "Todo task created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(implementation = ErrorAttributes.class)))
    public ResponseEntity<TodoTaskResponse> postTodoTask(@RequestBody @Valid TodoTaskRequest todoTaskRequest) {
        TodoTaskResponse todoTaskResponse = this.todoTaskService.createTodoTask(todoTaskRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(todoTaskResponse.id())
                .toUri();
        return ResponseEntity.created(location)
                .body(todoTaskResponse);
    }

    @GetMapping
    @Operation(summary = "Get todo tasks", description = "Retrieves a page of all todo tasks")
    @ApiResponse(responseCode = "200", description = "List of todo tasks retrieved successfully")
    public PagedModel<TodoTaskResponse> getTodoTasks(@ParameterObject Pageable pageable) {
        Page<TodoTaskResponse> todoTaskResponses = this.todoTaskService.getTodoTasks(pageable);
        return new PagedModel<>(todoTaskResponses);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get todo task by id", description = "Retrieves a todo task by its unique identifier")
    @ApiResponse(responseCode = "200", description = "Todo task retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Todo task not found", content = @Content(schema = @Schema(implementation = ErrorAttributes.class)))
    public ResponseEntity<TodoTaskResponse> getTodoTaskById(@PathVariable Long id) {
        TodoTaskResponse todoTaskResponse = this.todoTaskService.getTodoTask(id);
        return ResponseEntity.ok(todoTaskResponse);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update todo task by id", description = "Updates a todo task by its unique identifier")
    @ApiResponse(responseCode = "200", description = "Todo task updated successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(implementation = ErrorAttributes.class)))
    @ApiResponse(responseCode = "404", description = "Todo task not found", content = @Content(schema = @Schema(implementation = ErrorAttributes.class)))
    public ResponseEntity<TodoTaskResponse> putTodoTask(@PathVariable Long id, @RequestBody @Valid TodoTaskRequest todoTaskRequest) {
        TodoTaskResponse todoTaskResponse = this.todoTaskService.updateTodoTask(id, todoTaskRequest);
        return ResponseEntity.ok(todoTaskResponse);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete todo task by id", description = "Deletes a todo task by its unique identifier")
    @ApiResponse(responseCode = "204", description = "Todo task deleted successfully")
    public ResponseEntity<Void> deleteTodoTask(@PathVariable Long id) {
        this.todoTaskService.deleteTodoTask(id);
        return ResponseEntity.noContent()
                .build();
    }
}
