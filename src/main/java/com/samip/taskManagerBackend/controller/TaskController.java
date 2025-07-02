package com.samip.taskManagerBackend.controller;

import com.samip.taskManagerBackend.model.Task;
import com.samip.taskManagerBackend.model.User;
import com.samip.taskManagerBackend.service.TaskService;
import com.samip.taskManagerBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.samip.taskManagerBackend.model.UserPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin("*")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    // Get all tasks of the logged-in user
    @GetMapping
    public List<Task> getUserTasks(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return taskService.getTasksByUsername(userPrincipal.getUsername());
    }

    // Add a task
    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task task, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        User user = userService.findByUsername(userPrincipal.getUsername());
        task.setUser(user);
        task.setCreatedAt(new Date());
        return ResponseEntity.ok(taskService.saveTask(task));
    }

    // Delete task by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable int id, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        taskService.deleteTask(id, userPrincipal.getUsername());
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable int id, @RequestBody Task updatedTask,
                                           @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(taskService.updateTask(id, updatedTask, userPrincipal.getUsername()));
    }

}
