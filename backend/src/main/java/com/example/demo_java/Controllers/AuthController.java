package com.example.demo_java.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_java.User.UserService;
import com.example.demo_java.jwt.JwtTokenProvider;
import com.example.demo_java.roles.Roles;
import com.example.demo_java.roles.RoleService;
import com.example.demo_java.User.CustomUserDetails;
import com.example.demo_java.User.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager ;
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @GetMapping("/")
    public String home() {
        return "Welcome to the home page!";
    }
    @GetMapping("/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("Login successful!");
    }
    
    @CrossOrigin(origins = "http://localhost:9000")
    @PostMapping("/login")
    public String  login(@RequestBody LoginRequest loginRequest) {
        
     
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            String jwt = tokenProvider.generateToken(userDetails);
        return jwt;
        
    }
    @CrossOrigin(origins = "http://localhost:9000")
    @GetMapping("/random")
    public String randomStuff(){
        return "JWT Hợp lệ mới có thể thấy được message này";
    }
    
}