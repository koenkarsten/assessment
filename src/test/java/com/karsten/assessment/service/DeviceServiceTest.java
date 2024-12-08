package com.karsten.assessment.service;

import com.karsten.assessment.model.Device;
import com.karsten.assessment.model.DeviceType;
import com.karsten.assessment.repository.DeviceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeviceServiceTest {

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private DeviceService deviceService;

    private UUID deviceId;
    private Device device;

    @BeforeEach
    void setUp() {
        try (AutoCloseable autoCloseable = MockitoAnnotations.openMocks(this)) {
            deviceId = UUID.randomUUID();
            device = new Device(deviceId, DeviceType.WEB, "ModelX", "OSX");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testGetAll() {
        when(deviceRepository.findAll()).thenReturn(List.of(device));

        List<Device> devices = deviceService.getAll();

        assertNotNull(devices);
        assertEquals(1, devices.size());
        assertEquals(device, devices.get(0));

        verify(deviceRepository, times(1)).findAll();
    }

    @Test
    void testGetOne_Found() {
        when(deviceRepository.findById(deviceId)).thenReturn(Optional.of(device));

        Device result = deviceService.getOne(deviceId);

        assertNotNull(result);
        assertEquals(device, result);

        verify(deviceRepository, times(1)).findById(deviceId);
    }

    @Test
    void testGetOne_NotFound() {
        when(deviceRepository.findById(deviceId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> deviceService.getOne(deviceId));

        assertEquals(String.format("Device %s not found.", deviceId), exception.getMessage());

        verify(deviceRepository, times(1)).findById(deviceId);
    }

    @Test
    void testSave() {
        when(deviceRepository.save(device)).thenReturn(device);

        UUID savedDeviceId = deviceService.save(device);

        assertNotNull(savedDeviceId);
        assertEquals(deviceId, savedDeviceId);

        verify(deviceRepository, times(1)).save(device);
    }

    @Test
    void testDelete() {
        doNothing().when(deviceRepository).deleteById(deviceId);

        deviceService.delete(deviceId);

        verify(deviceRepository, times(1)).deleteById(deviceId);
    }
}
