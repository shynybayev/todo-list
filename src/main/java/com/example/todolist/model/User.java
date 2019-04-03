package com.example.todolist.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Класс модели пользователя
 * @author a.shynybayev
 * @version  2.0
 */
@Entity
@Data
public class User implements UserDetails {

    /**
     * свойство - ID(идентификатор) задачи c автоинкрементацией
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * свойство - имя пользователя
     */
    private String username;
    /**
     * свойство - пароль
     */
    private String password;

    /**
     * Свойство - уникальный электронный адрес
     */
    @Column(name = "email", unique = true)
    private String email;
    /**
     * Свойство - номер телефона
     */
    private String phone;

    /**
     * Свойство - множество ролей
     */
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name="roles")
    private Set<Role> roles = new HashSet<>();

    /**
     * Метод возвращающий коллекцию ролей
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    /**
     * реализация основных методов
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
