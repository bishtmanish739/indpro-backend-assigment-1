package com.backend.assignment.indpro.backend.assignment.repository;

import com.backend.assignment.indpro.backend.assignment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
