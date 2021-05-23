package com.example.todolist.repo;

import com.example.todolist.model.Fire;
import org.springframework.data.repository.CrudRepository;


public interface TaskRepository extends CrudRepository<Fire, Long> {
}
