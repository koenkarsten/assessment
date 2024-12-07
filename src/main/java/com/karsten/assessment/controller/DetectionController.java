package com.karsten.assessment.controller;

import com.karsten.assessment.model.Detection;
import com.karsten.assessment.model.Device;
import com.karsten.assessment.service.DetectionService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/1/detections")
public class DetectionController {
    private final DetectionService detectionService;

    private DetectionController(DetectionService detectionService) {
        this.detectionService = detectionService;
    }

    @GetMapping
    private ResponseEntity<List<Detection>> getDetections() {
        return ResponseEntity.ok(detectionService.getAllDetections());
    }

    @GetMapping("/{uid}")
    private ResponseEntity<Detection> getDetection(@PathVariable UUID uid) {
        final Detection detection = detectionService.getDetection(uid);
        return ResponseEntity.ok(detection);
    }

    //    @PutMapping("/{uid}")
//    private ResponseEntity<Void> saveDetectionForDevice(@PathVariable UUID uid, @RequestBody Detection detection) {
//        deviceService.processDetectionForDevice(uid, detection);
//        return ResponseEntity.ok().build();
//    }
}
