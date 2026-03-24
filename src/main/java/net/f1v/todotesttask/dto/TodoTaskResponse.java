package net.f1v.todotesttask.dto;

import net.f1v.todotesttask.entity.TodoStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import org.jspecify.annotations.Nullable;

import java.time.LocalDateTime;


public record TodoTaskResponse(
        @Schema(description = "Unique identifier of the task", example = "1")
        Long id,
        @Schema(description = "Title of the task", example = "Task title")
        String title,
        @Schema(description = "Description of the task", example = "Task description", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @Nullable
        String description,
        @Schema(description = "Status of the task", example = "NEW")
        TodoStatus status,
        @Schema(description = "Creation date and time of the task", example = "2026-03-15T00:00:00")
        LocalDateTime createdAt
) {
}
