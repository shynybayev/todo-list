package com.example.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoListApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoListApplication.class, args);
    }
/**
 * предзагрузка через CommandLineRunner
 */
//    @Bean
//    public CommandLineRunner commandLineRunner(TaskRepository taskRepository) {
//        return args -> {
//            var task = new Task();
//            task.setTitle("richard");
//            task.setDescription("Description");
//            task.setDate(LocalDate.now());
//            task.setDate(LocalDate.now());
//            task.setDate(LocalDate.now());
//
//            taskRepository.save(task);
//        };
//    }
}
