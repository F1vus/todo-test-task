package net.f1v.todotesttask.dto;

import net.f1v.todotesttask.entity.TodoStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.jspecify.annotations.Nullable;


public record TodoTaskRequest(
        @Schema(description = "Title of the task", example = "Task title")
        @NotBlank
        String title,
        @Schema(description = "Description of the task", example = "Task description", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @Nullable
        String description,
        @Schema(description = "Status of the task", example = "NEW")
        @NotNull
        TodoStatus status
) {
}
