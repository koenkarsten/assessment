package com.karsten.assessment.service;

import com.karsten.assessment.exception.DeviceException;
import com.karsten.assessment.model.Detection;
import com.karsten.assessment.repository.DetectionRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class DetectionService {
    final DetectionRepository detectionRepository;

    private DetectionService(DetectionRepository detectionRepository) {
        this.detectionRepository = detectionRepository;
    }

    public List<Detection> getAllDetections() {
        return detectionRepository.findAll();
    }

    public Detection getDetection(UUID uid) {
        final Optional<Detection> optionalDetection = detectionRepository.findById(uid);

        if(optionalDetection.isPresent()) {
            return optionalDetection.get();
        } else {
            throw new DeviceException("Detection %s not found.", uid);
        }
    }

    //    public void processDetectionForDevice(UUID deviceUid, Detection detection) {
//        Optional<Device> optionalDevice = deviceRepository.findById(deviceUid);
//
//        if(optionalDevice.isPresent()) {
//            Device device = optionalDevice.get();
//            resolveDetections(optionalDevice.get().detections(), detection);
//            deviceRepository.save(device);
//        } else {
//            throw new DeviceException("Device %s not found whilst processing Detection %s.", deviceUid, detection.uid());
//        }
//    }
//
//    private void resolveDetections(List<Detection> detections, Detection newDetection) {
//        switch (newDetection.type()) {
//            case NEW:
//                detections.add(newDetection);
//                break;
//            case RESOLVED:
//                detections.removeIf(x -> x.uid().equals(newDetection.uid()));
//                break;
//            case NONE:
//                detections.clear();
//                break;
//        }
//    }
}
