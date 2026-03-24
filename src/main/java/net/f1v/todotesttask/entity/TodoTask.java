package net.f1v.todotesttask.entity;

import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;

import java.time.LocalDateTime;


@Entity
@Table(name = "task", schema = "todo_service")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class TodoTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;

    @Column(name = "task_title", nullable = false)
    private String title;

    @Column(name = "task_description")
    private String description;

    @Column(name = "task_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TodoStatus status;

    @Column(name = "task_created_at", nullable = false)
    private LocalDateTime createdAt;

    /**
     * Creates a new task without an ID.
     *
     * @param title the task title
     * @param description optional task description
     * @param status the current task status
     * @param createdAt the creation timestamp
     */
    public TodoTask(String title,
                    @Nullable String description,
                    TodoStatus status,
                    LocalDateTime createdAt) {
        this(null, title, description, status, createdAt);
    }
}
