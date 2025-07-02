package com.samip.taskManagerBackend.service;

import com.samip.taskManagerBackend.dao.TaskRepo;
import com.samip.taskManagerBackend.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;

    public Task saveTask(Task task) {
        return taskRepo.save(task);
    }

    public List<Task> getTasksByUsername(String username) {
        return taskRepo.findByUser_Username(username);
    }

    public void deleteTask(int id, String username) {
        Optional<Task> taskOpt = taskRepo.findById(id);
        if (taskOpt.isPresent()) {
            Task task = taskOpt.get();
            if (task.getUser().getUsername().equals(username)) {
                taskRepo.deleteById(id);
            } else {
                throw new RuntimeException("Unauthorized delete attempt");
            }
        } else {
            throw new RuntimeException("Task not found");
        }
    }

    public Task updateTask(int id, Task updatedTask, String username) {
        Task existing = taskRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!existing.getUser().getUsername().equals(username)) {
            throw new SecurityException("Unauthorized");
        }

        existing.setTitle(updatedTask.getTitle());
        existing.setDescription(updatedTask.getDescription());
        existing.setDueDate(updatedTask.getDueDate());

        return taskRepo.save(existing);
    }

}
