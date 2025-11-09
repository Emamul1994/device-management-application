package com.device.mgmt.service.impl;

import com.device.mgmt.dto.DeviceCreateRequest;
import com.device.mgmt.dto.DeviceResponse;
import com.device.mgmt.dto.DeviceUpdateRequest;
import com.device.mgmt.entity.Device;
import com.device.mgmt.exception.ResourceNotFoundException;
import com.device.mgmt.repository.DeviceRepository;
import com.device.mgmt.service.IDeviceService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceServiceImpl implements IDeviceService {

    @Autowired
    private DeviceRepository repository;

    @Override
    @Transactional
    public DeviceResponse createDevice(DeviceCreateRequest request) {
        if (repository.existsByDeviceId(request.getDeviceId())) {
            throw new IllegalArgumentException("Device with ID " + request.getDeviceId() + " already exists.");
        }
        Device device = new Device();
        device.setDeviceId(request.getDeviceId());
        device.setUserId(request.getUserId());
        device.setDeviceName(request.getDeviceName());
        device.setDeviceType(request.getDeviceType());
        device.setStatus("ACTIVE");
        Device saveDevice = repository.save(device);
        DeviceResponse response = mapToResponse(saveDevice);
        return response;
    }

    @Override
    @Transactional
    public List<DeviceResponse> getDevicesByUserId(String userId) {
        return repository
                .findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional
    public DeviceResponse getDeviceById(String deviceId) {
        return repository
                .findByDeviceId(deviceId)
                .map(this::mapToResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Device not found with ID: " + deviceId));
    }

    @Override
    @Transactional
    public DeviceResponse updateDevice(String deviceId, DeviceUpdateRequest request) {
        Device device = repository
                .findByDeviceId(deviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Device not found with ID: " + deviceId));
        Optional.ofNullable(request.getDeviceName()).ifPresent(device::setDeviceName);
        Optional.ofNullable(request.getDeviceType()).ifPresent(device::setDeviceType);
        Optional.ofNullable(request.getStatus()).ifPresent(device::setStatus);
        Device devResponse = repository.save(device);
        DeviceResponse response = mapToResponse(devResponse);
        return response;
    }

    @Override
    @Transactional
    public void deleteDevice(String deviceId) {

        if (!repository.existsByDeviceId(deviceId)) {
            throw new ResourceNotFoundException("Device not found with ID: " + deviceId);
        }
        repository.deleteByDeviceId(deviceId);
    }

    private DeviceResponse mapToResponse(Device device) {
        var response = new DeviceResponse();
        response.setDeviceId(device.getDeviceId());
        response.setUserId(device.getUserId());
        response.setDeviceName(device.getDeviceName());
        response.setDeviceType(device.getDeviceType());
        response.setStatus(device.getStatus());

        response.setCreatedAt(
                Optional.ofNullable(device.getCreatedAt())
                        .map(Object::toString)
                        .orElse(null)
        );

        response.setUpdatedAt(
                Optional.ofNullable(device.getUpdatedAt())
                        .map(Object::toString)
                        .orElse(null)
        );

        return response;
    }
}
