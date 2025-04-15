package com.example.demo_java.roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {
    Roles  findById(Integer id) ;
    
}
