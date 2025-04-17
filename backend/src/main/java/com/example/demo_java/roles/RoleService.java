package com.example.demo_java.roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    public Roles getRoleById(Integer id) {
        return roleRepository.findById(id);
    }
}
