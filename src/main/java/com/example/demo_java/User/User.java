package com.example.demo_java.User;

import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import com.example.demo_java.roles.Roles;
@Data
@Entity 
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String username;
    private String password;
    @OneToOne
    @JoinColumn(name = "role_id")
    private Roles roles;
  
  }