package com.example.todolist.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Класс Task, модель задачи
 * @author a.shynybayev
 * @version 2.0
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    /**
     * свойство - ID(идентификатор) задачи c автоинкрементацией
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * свойство - заголовок задачи
     * @Size - аннотация с помощью которого ограничиваем длину минимального значения
     */
    @Size(min = 3, message = "min 3 characters")
    private String title;

    /**
     * свойство описание задачи
     */
    @Size(min = 5, message = "min 5 characters")
    private String description;

    /**
     * свойство - дата выполнения задачи c форматом
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    /**
     * Статус задачи
     */
    private boolean status;

}
