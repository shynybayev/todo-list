package com.example.todolist.model;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

/**
 * Класс формы регистрации пользователей
 * @author a.shynybayev
 * @version 2.0
 */
@Data
public class RegistrationForm {
    /**
     * Свойство - имя пользователя
     */
    private String username;
    /**
     * Свойство - пароль
     */
    private String password;
    /**
     * Свойство - почтовый адрес
     */
    private String email;
    /**
     * Свойство - номерт телефона
     */
    private String phone;

    /**
     * Метод который присваивает введенные данные
     */
    public User toUser(PasswordEncoder passwordEncoder) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setPhone(phone);
        user.setUsername(username);
        user.setRoles(Collections.singleton(Role.USER));
        return user;
    }
}
