package com.example.codefellowship;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<ApplicationUser,Integer> {
    public ApplicationUser findByUsername(String userName);
}
