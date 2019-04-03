package com.example.todolist.repo;

import com.example.todolist.model.Task;
import org.springframework.data.repository.CrudRepository;

/**
 * Интерфейс репозиторий, предоставляющий для модели Задачи классические CRUD операции
 * @author a.shynybayev
 * @version 2.0
 */
public interface TaskRepository extends CrudRepository<Task, Long> {
}
