package com.samip.task_manager_backend.controller;

import com.samip.task_manager_backend.model.Task;
import com.samip.task_manager_backend.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TaskController.java
@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private TaskService taskService;


    @GetMapping("csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @GetMapping()
    public ResponseEntity<List<Task>> getTasks(@RequestParam String email) {
        return new ResponseEntity<>(taskService.getTasksByEmail(email), org.springframework.http.HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        Task saved = taskService.addTask(task);
        return ResponseEntity.ok(saved);
    }


    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable int taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

}
