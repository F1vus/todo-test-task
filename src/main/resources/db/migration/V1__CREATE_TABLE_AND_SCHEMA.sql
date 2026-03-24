
CREATE SCHEMA IF NOT EXISTS todo_service;

CREATE TABLE IF NOT EXISTS todo_service.task (
    task_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    task_title VARCHAR(255) NOT NULL,
    task_description TEXT,
    task_status VARCHAR(255) NOT NULL,
    task_created_at TIMESTAMP NOT NULL
);