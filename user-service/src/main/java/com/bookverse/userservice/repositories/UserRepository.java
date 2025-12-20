package com.bookverse.userservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookverse.userservice.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}