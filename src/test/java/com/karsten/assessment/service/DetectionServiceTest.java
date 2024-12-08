package com.karsten.assessment.service;


import com.karsten.assessment.model.Detection;
import com.karsten.assessment.model.DetectionType;
import com.karsten.assessment.repository.DetectionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DetectionServiceTest {

    @Mock
    private DetectionRepository detectionRepository;

    @InjectMocks
    private DetectionService detectionService;

    private UUID deviceId;
    private Detection detection1;
    private Detection detection2;

    @BeforeEach
    void setUp() {
        try (AutoCloseable autoCloseable = MockitoAnnotations.openMocks(this)) {
            deviceId = UUID.randomUUID();
            detection1 = new Detection(UUID.randomUUID(), deviceId, DetectionType.NEW, 1733582900L, "App1", "Type1");
            detection2 = new Detection(UUID.randomUUID(), deviceId, DetectionType.RESOLVED, 1733582950L, "App2", "Type2");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testGetDetectionsWithFiltering_NoFilters() {
        when(detectionRepository.findByOrderByTimeDesc(null, null, null)).thenReturn(List.of(detection1, detection2));

        List<Detection> detections = detectionService.getDetectionsWithFiltering(null, null, null);

        assertEquals(2, detections.size());
        assertEquals(detection1, detections.get(0));
        verify(detectionRepository, times(1)).findByOrderByTimeDesc(null, null, null);
    }

    @Test
    void testGetDetectionsWithFiltering_WithFilters() {
        when(detectionRepository.findByOrderByTimeDesc(deviceId, "App1", "Type1")).thenReturn(List.of(detection1));

        List<Detection> detections = detectionService.getDetectionsWithFiltering(deviceId, "App1", "Type1");

        assertEquals(1, detections.size());
        assertEquals(detection1, detections.get(0));
        verify(detectionRepository, times(1)).findByOrderByTimeDesc(deviceId, "App1", "Type1");
    }

    @Test
    void testGetDetectionsWithFiltering_NoMatchingResults() {
        when(detectionRepository.findByOrderByTimeDesc(deviceId, "NonExistentApp", "NonExistentType")).thenReturn(List.of());

        List<Detection> detections = detectionService.getDetectionsWithFiltering(deviceId, "NonExistentApp", "NonExistentType");

        assertEquals(0, detections.size());
        verify(detectionRepository, times(1)).findByOrderByTimeDesc(deviceId, "NonExistentApp", "NonExistentType");
    }

    @Test
    void testStoreDetection() {
        Detection savedDetection = new Detection(UUID.randomUUID(), deviceId, DetectionType.NEW, 1733583000L, "App3", "Type3");

        when(detectionRepository.save(detection1)).thenReturn(savedDetection);

        UUID storedDetectionId = detectionService.storeDetection(detection1);

        assertEquals(savedDetection.uid(), storedDetectionId);
        verify(detectionRepository, times(1)).save(detection1);
    }
}