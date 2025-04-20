package org.example.login_Service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.example.login_Service.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}