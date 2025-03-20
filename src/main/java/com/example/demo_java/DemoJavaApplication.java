package com.example.demo_java;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo_java.User.User;
import com.example.demo_java.User.UserRepository;


@SpringBootApplication
public class DemoJavaApplication{

	public static void main(String[] args) {
		// PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		// String rawPassword = "123456"; 

        // // Encode the password
        // String encodedPassword = passwordEncoder.encode(rawPassword);

        // System.out.println("Encoded Password: " + encodedPassword);
		SpringApplication.run(DemoJavaApplication.class, args);
	}
}
