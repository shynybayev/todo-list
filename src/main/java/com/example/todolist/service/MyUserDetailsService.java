package com.example.todolist.service;

import com.example.todolist.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Класс реализация  UserDetailsService
 * @author a.shynybayev
 * @version 2.0
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    /**
     * Свойство - Репозиторий пользователя
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Метод для авторизации по имени пользователя
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
