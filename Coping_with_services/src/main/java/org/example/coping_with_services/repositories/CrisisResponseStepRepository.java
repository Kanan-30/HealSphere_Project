package org.example.coping_with_services.repositories;
import org.example.coping_with_services.model.CrisisResponseStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrisisResponseStepRepository extends JpaRepository<CrisisResponseStep, Long> {}