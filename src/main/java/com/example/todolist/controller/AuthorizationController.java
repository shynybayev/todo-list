package com.example.todolist.controller;


import com.example.todolist.logs.Loggable;
import com.example.todolist.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Класс контроллер отвечающий за авторизацию
 * @author a.shynybayev
 * @version 2.0
 */
@Controller
@RequestMapping("/")
public class AuthorizationController {
    /**
     * метод запроса аутентификации с именем пользователя
     */
    @Loggable
    @GetMapping
    public String index(@AuthenticationPrincipal User user, Model model) {

        if (user != null) {
            model.addAttribute("user", user.getUsername());
            return "home";
        }
        model.addAttribute("user", "anonymous");
        return "home";
    }

    /**
     * Метод формы для входа в логин
     */
    @Loggable
    @GetMapping("/login")
    public String login() {
         return "login";
    }

    /**
     * Метод формы для входа по ролю пользователям
     */
    @Loggable
    @PreAuthorize(value = "hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/foruser")
    public String forUser() {
        return "foruser";
    }

    /**
     * Метод формы для входа админов
     */
    @Loggable
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @GetMapping("/foradmin")
    public String forAdmin() {
        return "foradmin";
    }
}