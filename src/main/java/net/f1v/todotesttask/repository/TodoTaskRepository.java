package net.f1v.todotesttask.repository;

import net.f1v.todotesttask.entity.TodoTask;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TodoTaskRepository extends JpaRepository<TodoTask, Long> { }
