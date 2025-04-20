package org.example.coping_with_services.repositories;

import org.example.coping_with_services.model.UserCrisisPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserCrisisPlanRepository extends JpaRepository<UserCrisisPlan, Long> {
    Optional<UserCrisisPlan> findByUser_Id(Long userId);
}
