package com.example.demo_java.User;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }

    public UserDetails loadUserById(Long userId) {
        // TODO Auto-generated method stub
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User not found with ID: " + userId);
        }
        return new CustomUserDetails(user.get());
    }


}


// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// @Service
// public class UserService {

//     @Autowired
//     private UserRepository userRepository;

//     // Tìm người dùng theo username và password
//     public User getUserByUsernameAndPassword(String username, String password) {
//         return userRepository.findByUsernameAndPassword(username, password);
//     }
// }