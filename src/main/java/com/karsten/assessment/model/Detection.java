package com.karsten.assessment.model;

import org.springframework.data.annotation.Id;

import java.util.UUID;

public record Detection(@Id UUID uid, UUID deviceKey, DetectionType type, Long time, String appName, String appType) {
}
