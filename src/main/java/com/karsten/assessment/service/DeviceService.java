package com.karsten.assessment.service;

import com.karsten.assessment.exception.DeviceException;
//import com.karsten.assessment.model.Detection;
import com.karsten.assessment.model.Device;
import com.karsten.assessment.repository.DeviceRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// Design Consideration: This middleware layer between the REST and Repository layer gives a clear place to put business
// logic. It also provides a good point of standardisation wrt usage of optionals and error throwing/handling. This
// design decision together with the usage of a @RestControllerAdvice makes the codebase clean and concise.

// Responsibilities:
// - Handle business logic.
// - Convert workflow from Optional based to Exception based.
@Component
public class DeviceService {
    final DeviceRepository deviceRepository;

    private DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public Device getDevice(UUID uid) {
        final Optional<Device> optionalDevice = deviceRepository.findById(uid);

        if(optionalDevice.isPresent()) {
            return optionalDevice.get();
        } else {
            throw new DeviceException("Device %s not found.", uid);
        }
    }
}
