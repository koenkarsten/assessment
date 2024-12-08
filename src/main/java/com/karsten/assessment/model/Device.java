package com.karsten.assessment.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("DEVICE")
public record Device(@Id UUID uid, DeviceType type, String model, String os) {
}
