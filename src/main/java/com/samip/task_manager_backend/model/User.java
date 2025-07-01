package com.samip.task_manager_backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @Column(nullable = false, unique = true)
    private String email;  // Primary key

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Date createdAt;
    private Date updatedAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @com.fasterxml.jackson.annotation.JsonIgnore
    private List<Task> tasks;
}
