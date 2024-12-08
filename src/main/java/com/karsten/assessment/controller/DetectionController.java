package com.karsten.assessment.controller;

import com.karsten.assessment.model.Detection;
import com.karsten.assessment.service.DetectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/1/detections")
public class DetectionController {
    private final DetectionService detectionService;

    private DetectionController(DetectionService detectionService) {
        this.detectionService = detectionService;
    }

    // API Specification 2.1.1.2
    @GetMapping
    public ResponseEntity<List<Detection>> getDetections(@RequestParam(required = false) UUID device,
                                                          @RequestParam(required = false) String appName,
                                                          @RequestParam(required = false) String appType) {
        return ResponseEntity.ok(detectionService.getDetectionsWithFiltering(device, appName, appType));
    }

    // API Specification 2.1.1.1
    @PostMapping
    public ResponseEntity<Void> storeDetection(@RequestBody Detection detection) {
        URI uri = URI.create(detectionService.storeDetection(detection).toString());
        return ResponseEntity.created(uri).build();
    }
}
