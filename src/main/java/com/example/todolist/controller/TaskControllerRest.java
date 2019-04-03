package com.example.todolist.controller;

import com.example.todolist.logs.Loggable;
import com.example.todolist.model.Task;
import com.example.todolist.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

/**
 * Класс RESTFul Api контроллера в котором отдаем jSon Ответ
 * @author a.shynybayev
 * @version 2.0
 */
@RestController
@RequestMapping(value = {"/api"}, produces = "application/json")
public class TaskControllerRest {
    /**
     * С помощью этого поля будем выполнять основные CRUD операции для задачи
     */
    @Autowired
    private TaskRepository taskRepository;

    /**
     * Внедрение зависимости через конструктор
     */
    @Autowired
    public TaskControllerRest(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Метод возвращающий весь список задач
     */
    @GetMapping
    public Iterable<Task> getAllTasks(){
       return  taskRepository.findAll();
    }

    /**
     * Метод возвращающий задачу по ID
     */
    @Loggable
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id")Long id){
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()){
            return new  ResponseEntity<>(task.get(), HttpStatus.OK); //данные помимо нашего тела
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    /**
     * Метод принимающий в  качестве параметра json объект и сохраняет значение в хранилище
     */
    @Loggable
    @PostMapping(consumes = "application/json")
    public Task postTask(@RequestBody Task task){
        return taskRepository.save(task);
    }

    /**
     * Метод удаляющий запись по Id
     */
    @Loggable
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable("id") Long id){
        try {
            taskRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
        }
    }

    /**
     * Обновление задачи по ID
     * @return
     */
    @Loggable
    @PutMapping("/{id}")
    public Task putTask(@RequestBody Task task){
        return taskRepository.save(task);
    }

    /**
     * Обновление задачи по ID
     * метод который потребляют JSON
     * @return
     */
    @Loggable
    @PatchMapping(path = "/{id}", consumes = "application/json")
    public Task patchTask(@PathVariable("id")Long id, @RequestBody Task taskPath){
        Task taskRefresh = taskRepository.findById(id).get();
        if (taskPath.getTitle() != null){
            taskRefresh.setTitle(taskPath.getTitle());
        }
        if (taskPath.getDescription() != null){
            taskRefresh.setDescription(taskPath.getDescription());
        }

        if (taskPath.getDate() != null){
            taskRefresh.setDate(taskPath.getDate());
        }

        return taskRepository.save(taskPath);
    }

}