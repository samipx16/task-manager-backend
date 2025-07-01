package com.samip.springsecdemo.dao;

import com.samip.springsecdemo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Integer> {


    List<Task> findByUser_Username(String username);
}
