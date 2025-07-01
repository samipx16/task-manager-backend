package com.samip.task_manager_backend.service;

import com.samip.task_manager_backend.dao.TaskDao;
import com.samip.task_manager_backend.dao.UserDao;
import com.samip.task_manager_backend.model.Task;
import com.samip.task_manager_backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TaskService {
    @Autowired
    private TaskDao taskRepo;

    @Autowired
    private UserDao userDao;

    public List<Task> getTasksByEmail(String email) {
        return taskRepo.findByUserEmail(email);
    }

    public Task addTask(Task task) {
        // Find the user by email before assigning to task
        String email = task.getUser().getEmail();
        User existingUser = userDao.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found: " + email));

        task.setUser(existingUser); // Set fully-managed user
        return taskRepo.save(task);
    }

    public void deleteTask(int taskId) {
        taskRepo.deleteById(taskId);
    }

}
