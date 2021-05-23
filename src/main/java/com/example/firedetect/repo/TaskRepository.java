package com.example.firedetect.repo;

import com.example.firedetect.model.Fire;
import org.springframework.data.repository.CrudRepository;


public interface TaskRepository extends CrudRepository<Fire, Long> {
}
