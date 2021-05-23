package com.example.todolist.controller;

import com.example.todolist.logs.Loggable;
import com.example.todolist.model.Fire;
import com.example.todolist.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/todo")
public class FireDetectController {

    @Autowired
    private TaskRepository taskRepository;


    @Autowired
    public FireDetectController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    @Loggable
    public String getIndex(Model model){
        model.addAttribute("fires", taskRepository.findAll());
        model.addAttribute("newFire", new Fire());
        return "index";
    }

    /**
     * Метод для создание новой задачи
     */
    @Loggable
    @PostMapping("/")
    public String createTask(Fire fire, Model model){
        try {
            taskRepository.save(fire);
            model.addAttribute("fire", new Fire());
        }catch (Exception e) {
            model.addAttribute("fire", fire);
            model.addAttribute("error", "failed");
        }
        return "redirect:/todo";
    }


    @Loggable
    @GetMapping("/{id}/show")
    public String showById(@PathVariable("id") Long id, Model model){
        if(taskRepository.findById(id).isPresent()){
            model.addAttribute("fire", taskRepository.findById(id).get());
            return "show";
        }
        model.addAttribute("fire", new Fire());
        return "show";
    }

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
            model.addAttribute("fire", taskRepository.findById(id).get());
            return "edit";
        }
        model.addAttribute("fire", new Fire());
        return "edit";
    }


    @Loggable
    @PostMapping("/{id}")
    public String updateTask(@PathVariable Long id, @Valid Fire fire, Errors errors){
        if (errors.hasErrors()){
            return "edit";
        }
        taskRepository.save(fire);
        return "redirect:/todo";
    }

}
