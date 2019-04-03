package com.example.todolist.controller;

import com.example.todolist.logs.Loggable;
import com.example.todolist.model.RegistrationForm;
import com.example.todolist.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Класс контроллер для регистрации
 * @author a.shynybayev
 * @version 2.0
 */
@Controller
@RequestMapping("/registration")
public class RegistrationController {
    /**
     * Свойство хэширование пароля
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Свойство с CRUD операциями
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Метод формы регистрации
     * @return
     */
    @Loggable
    @GetMapping
    public String registration() {
        return "registration";
    }

    /**
     * Метод для отправки данных
     */
    @Loggable
    @PostMapping
    public String processUser(RegistrationForm form) {
        userRepository.save(form.toUser(passwordEncoder));
        return "redirect:/login";
    }
}
