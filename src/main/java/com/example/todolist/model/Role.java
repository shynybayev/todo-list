package com.example.todolist.model;


import org.springframework.security.core.GrantedAuthority;

/**
 * Перечисление Ролей
 * @author a.shynybayev
 * @version 2.0
 */
public enum Role implements GrantedAuthority {
    USER, ADMIN;

    /**
     * Метод возвращающий имя роля
     */
    @Override
    public String getAuthority() {
        return name();
    }
}
