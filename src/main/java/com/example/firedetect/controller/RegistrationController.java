package com.example.firedetect.controller;

import com.example.firedetect.logs.Loggable;
import com.example.firedetect.model.RegistrationForm;
import com.example.firedetect.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private UserRepository userRepository;


    @Loggable
    @GetMapping
    public String registration() {
        return "registration";
    }


    @Loggable
    @PostMapping
    public String processUser(RegistrationForm form) {
        userRepository.save(form.toUser(passwordEncoder));
        return "redirect:/login";
    }
}
