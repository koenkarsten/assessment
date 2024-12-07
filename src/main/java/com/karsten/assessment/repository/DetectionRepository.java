package com.karsten.assessment.repository;

import com.karsten.assessment.model.Detection;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DetectionRepository extends ListCrudRepository<Detection, UUID> {
}
