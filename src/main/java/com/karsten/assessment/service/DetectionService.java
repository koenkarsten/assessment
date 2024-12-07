package com.karsten.assessment.service;

import com.karsten.assessment.model.Detection;
import com.karsten.assessment.repository.DetectionRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class DetectionService {
    final DetectionRepository detectionRepository;

    private DetectionService(DetectionRepository detectionRepository) {
        this.detectionRepository = detectionRepository;
    }

    public List<Detection> getDetectionsWithFiltering(UUID device, String appName, String appType) {
        return detectionRepository.findByOrderByTimeDesc(device, appName, appType).stream()
            .filter(detection -> device == null || detection.device().equals(device))
            .filter(detection -> appName == null || detection.appName().equals(appName))
            .filter(detection -> appType == null || detection.appType().equals(appType))
            .collect(Collectors.toList());
    }


    public UUID storeDetection(Detection detection) {
        return detectionRepository.save(detection).uid();
    }
}
