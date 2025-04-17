package com.example.demo_java.User;

import lombok.Data;

// import java.util.ArrayList;
// import java.util.Collection;
// import java.util.Collections;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import lombok.AllArgsConstructor;
// import lombok.Data;

// @Service
// public class CustomUserDetails implements UserDetailsService  {
//     private final UserService userService; // assuming you have a UserService for your DB operations

//     public CustomUserDetails(UserService userService) {
//         this.userService = userService;
//     }

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         User user = userService.getUserByUsername(username);
//         if (user == null) {
//             throw new UsernameNotFoundException("User not found with username: " + username);
//         }
//         return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
//     }
// }
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
    User user;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Mặc định mình sẽ để tất cả là ROLE_USER. Để demo cho đơn giản.
        String role="ROLE_"+getRole().toUpperCase();
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }
    
    @Autowired
    public String getRole(){
        return user.getRoles().getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}