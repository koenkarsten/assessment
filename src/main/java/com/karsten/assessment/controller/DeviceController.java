package com.karsten.assessment.controller;

import com.karsten.assessment.model.Device;
import com.karsten.assessment.service.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
    public ResponseEntity<List<Device>> getDevices() {
        return ResponseEntity.ok(deviceService.getAll());
    }

    @GetMapping("/{identifier}")
    public ResponseEntity<Device> getDevice(@PathVariable UUID identifier) {
        final Device device = deviceService.getOne(identifier);
        return ResponseEntity.ok(device);
    }

    @PostMapping
    public ResponseEntity<Void> addDevice(@RequestBody Device device) {
        URI uri = URI.create(deviceService.save(device).toString());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    public ResponseEntity<Void> updateDevice(@RequestBody Device device) {
        deviceService.save(device);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{identifier}")
    public ResponseEntity<Void> deleteDevice(@PathVariable UUID identifier) {
        deviceService.delete(identifier);
        return ResponseEntity.accepted().build();
    }
}

