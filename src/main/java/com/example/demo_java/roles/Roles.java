package com.example.demo_java.roles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

import com.example.demo_java.User.User; // Import the User class

@Data
@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToOne(mappedBy = "roles") // Ensure this matches the field name in the User entity
    private User user; // Add the associated User field
}