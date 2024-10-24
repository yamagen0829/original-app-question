package com.example.musiccollection.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.musiccollection.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    public User findByUserEmail(String userEmail);
}
