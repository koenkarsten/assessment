package com.karsten.assessment.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("Detection")
public record Detection(@Id UUID uid, UUID device, DetectionType type, Long time, String appName, String appType) {
}
