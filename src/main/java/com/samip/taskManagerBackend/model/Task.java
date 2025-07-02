package com.samip.taskManagerBackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
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
    @JoinColumn(name = "user_id")// FK to User entity
    private User user;
}
