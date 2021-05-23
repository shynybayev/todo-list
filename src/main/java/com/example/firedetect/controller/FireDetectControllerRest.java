package com.example.firedetect.controller;

import com.example.firedetect.logs.Loggable;
import com.example.firedetect.model.Fire;
import com.example.firedetect.model.StaticDto;
import com.example.firedetect.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping(value = {"/api"}, produces = "application/json")
public class FireDetectControllerRest {
    @Value("${temperature}")
    private String temp;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    public FireDetectControllerRest(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PostMapping(value = "/postbody", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> postBody(@RequestBody StaticDto dto) {
        return new ResponseEntity<>(200, HttpStatus.OK);
    }

    @GetMapping
    public Iterable<Fire> getAllTasks(){
       return  taskRepository.findAll();
    }

    @Loggable
    @GetMapping("/{id}")
    public ResponseEntity<String> getTaskById(@PathVariable("id")Long id){
        Optional<Fire> task = taskRepository.findById(id);
        if (task.isPresent()){
            return new  ResponseEntity<>("success", HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Loggable
    @PostMapping(consumes = "application/json")
    public Fire postTask(@RequestBody Fire fire){
        return taskRepository.save(fire);
    }

    @Loggable
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable("id") Long id){
        try {
            taskRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
        }
    }


    @Loggable
    @PostMapping("/test")
    public String putTask(@RequestBody String fire){
        return "success";
    }


    @Loggable
    @PatchMapping(path = "/{id}", consumes = "application/json")
    public Fire patchTask(@RequestBody Fire firePath){
        Fire fireRefresh = taskRepository.findById(firePath.getId()).get();
        if (firePath.getTitle() != null){
            fireRefresh.setTitle(firePath.getTitle());
        }
        if (firePath.getDescription() != null){
            fireRefresh.setDescription(firePath.getDescription());
        }
        if (firePath.getDate() != null){
            fireRefresh.setDate(firePath.getDate());
        }
        if (firePath.getTemperature().compareTo(temp) > 0) {
            firePath.setStatus(true);
        } else {
            firePath.setStatus(false);
        }
        return taskRepository.save(firePath);
    }

}