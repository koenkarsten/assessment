package com.karsten.assessment.controller;

//import com.karsten.assessment.model.Detection;
import com.karsten.assessment.model.Device;
import com.karsten.assessment.service.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/1/devices")
public class DeviceController {
    private final DeviceService deviceService;

    private DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping
    private ResponseEntity<List<Device>> getDevices() {
        return ResponseEntity.ok(deviceService.getAllDevices());
    }

    @GetMapping("/{uid}")
    private ResponseEntity<Device> getDevice(@PathVariable UUID uid) {
        final Device device = deviceService.getDevice(uid);
        return ResponseEntity.ok(device);
    }

//    @PutMapping("/{uid}")
//    private ResponseEntity<Void> saveDetectionForDevice(@PathVariable UUID uid, @RequestBody Detection detection) {
//        deviceService.processDetectionForDevice(uid, detection);
//        return ResponseEntity.ok().build();
//    }
}
