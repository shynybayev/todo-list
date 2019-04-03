package com.example.todolist.controller;

import com.example.todolist.logs.Loggable;
import com.example.todolist.model.Task;
import com.example.todolist.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 *  Класс Контроллер который возвращает представление
 *  @author a.shynybayev
 *  @version 2.0
 */

@Controller
@RequestMapping("/todo")
public class TaskController {
    /**
     * С помощью этого поля будем выполнять основные CRUD операции для задачи
     */
    @Autowired
    private TaskRepository taskRepository;

    /**
     * Внедрение зависимости через конструктор
     */
    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Метод, который загружает весь список задач которое есть в хранилище
     */
    @GetMapping
    @Loggable
    public String getIndex(Model model){
        model.addAttribute("tasks", taskRepository.findAll());
        model.addAttribute("newTask", new Task());
        return "index";
    }

    /**
     * Метод для создание новой задачи
     */
    @Loggable
    @PostMapping("/")
    public String createTask(Task task, Model model){
        try {
            taskRepository.save(task); //отобразили на одной странице
            model.addAttribute("task", new Task());
        }catch (Exception e) {
            model.addAttribute("task", task);
            model.addAttribute("error", "failed");
        }
        return "redirect:/todo";
    }

    /**
     * Метод показывающий задачу по Id
     */
    @Loggable
    @GetMapping("/{id}/show")
    public String showById(@PathVariable("id") Long id, Model model){
        if(taskRepository.findById(id).isPresent()){
            model.addAttribute("task", taskRepository.findById(id).get());
            return "show";
        }
        model.addAttribute("task", new Task());
        return "show";
    }

    /**
     * Метод для удаления задачи по ID
     */
    @Loggable
    @GetMapping("/{id}/delete")
    public String deleteById(@PathVariable("id") Long id){
        taskRepository.deleteById(id);
        return "redirect:/todo";
    }

    /**
     * GET Метод для редактирования задачи по ID
     */
    @Loggable
    @GetMapping("/{id}/edit")
    public String editFormById(@PathVariable("id") Long id, Model model){

        if(taskRepository.findById(id).isPresent()){
            model.addAttribute("task", taskRepository.findById(id).get());
            return "edit";
        }
        model.addAttribute("task", new Task());
        return "edit";
    }

    /**
     * POST Метод для редактирования задачи по ID
     */
    @Loggable
    @PostMapping("/{id}")
    public String updateTask(@PathVariable Long id, @Valid Task task, Errors errors){
        if (errors.hasErrors()){
            return "edit"; //ТУ же самую страничку возвращаю
        }
        taskRepository.save(task);
        return "redirect:/todo";
    }

}
