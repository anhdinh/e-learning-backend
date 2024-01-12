package com.andy.elearning.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String password;
    private boolean active;
    private boolean admin;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    @OneToMany
    Set<Role> roles;

}
