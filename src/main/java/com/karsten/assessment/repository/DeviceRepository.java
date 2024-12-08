package com.karsten.assessment.repository;

import com.karsten.assessment.model.Device;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeviceRepository extends ListCrudRepository<Device, UUID> {
}

