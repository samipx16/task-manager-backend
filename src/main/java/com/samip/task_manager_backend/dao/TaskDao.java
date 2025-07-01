package com.samip.task_manager_backend.dao;

import com.samip.task_manager_backend.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskDao extends JpaRepository<Task,Integer> {

    List<Task> findByUserEmail(String email);

    void deleteById(int taskId);
}
