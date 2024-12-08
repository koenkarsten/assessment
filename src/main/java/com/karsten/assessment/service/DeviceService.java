package com.karsten.assessment.service;

import com.karsten.assessment.model.Device;
import com.karsten.assessment.repository.DeviceRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class DeviceService {
    final DeviceRepository deviceRepository;

    private DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public List<Device> getAll() {
        return deviceRepository.findAll();
    }

    public Device getOne(UUID uid) {
        final Optional<Device> optionalDevice = deviceRepository.findById(uid);

        if(optionalDevice.isPresent()) {
            return optionalDevice.get();
        } else {
            throw new RuntimeException(String.format("Device %s not found.", uid));
        }
    }

    public UUID save(Device device) {
        return deviceRepository.save(device).uid();
    }

    public void delete(UUID uuid) {
        deviceRepository.deleteById(uuid);
    }
}

