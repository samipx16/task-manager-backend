package com.samip.task_manager_backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskId;

    private String title;
    private String description;
    private Date createdAt;
    private Date dueDate;

    @ManyToOne
    @JoinColumn(name = "user_email", referencedColumnName = "email") // foreign key
    private User user;
}
