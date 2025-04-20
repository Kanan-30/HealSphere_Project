package org.example.coping_with_services.repositories;

import org.example.coping_with_services.model.CrisisDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CrisisDescriptionRepository extends JpaRepository<CrisisDescription, Long> {}