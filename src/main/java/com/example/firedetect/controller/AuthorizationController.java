package com.example.firedetect.controller;


import com.example.firedetect.logs.Loggable;
import com.example.firedetect.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class AuthorizationController {

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


    @Loggable
    @GetMapping("/login")
    public String login() {
         return "login";
    }


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