package org.example.self_discovery_service.repository;

import org.example.self_discovery_service.entity.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserResponseRepository extends JpaRepository<UserResponse, Long> {
    List<UserResponse> findByUserId(Long userId);
}
