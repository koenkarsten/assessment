package com.karsten.assessment.repository;

import com.karsten.assessment.model.Detection;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DetectionRepository extends ListCrudRepository<Detection, UUID> {
    List<Detection> findByOrderByTimeDesc(UUID device, String appName, String appType);
}
