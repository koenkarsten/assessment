package com.karsten.assessment.model;

import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.UUID;

public record Device(@Id UUID uid, DeviceType type, String model, String os, List<Detection> detections) {

}



