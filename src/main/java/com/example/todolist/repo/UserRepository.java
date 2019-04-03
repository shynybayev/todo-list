package com.example.todolist.repo;

import com.example.todolist.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Интерфейс репозиторий, предоставляющий для модели User классические CRUD операции
 * @author a.shynybayev  @
 * version 2.0
 */
public interface UserRepository extends CrudRepository<User, Long> {
    /**
     * Метод поиска по имени
     */
    User findByUsername(String name);
}
