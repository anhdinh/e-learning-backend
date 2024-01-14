package com.andy.elearning.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "users_roles")
@Setter
@Getter
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;

    @Override
    public int hashCode() {
        return Objects.hash(user, role);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UserRole userRole = (UserRole) obj;
        return Objects.equals(user, userRole.user) &&
                Objects.equals(role, userRole.role);
    }
}
