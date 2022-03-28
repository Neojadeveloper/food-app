package com.example.pdp_meal.repository;

import com.example.pdp_meal.entity.Uploads;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UploadsRepository extends JpaRepository<Uploads,Long> {
    Optional<Uploads>findByGeneratedName(String filename);
}
