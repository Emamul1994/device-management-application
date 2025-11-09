package com.device.mgmt.controller;

import com.device.mgmt.dto.DeviceCreateRequest;
import com.device.mgmt.dto.DeviceResponse;
import com.device.mgmt.dto.DeviceUpdateRequest;
import com.device.mgmt.service.IDeviceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    @Autowired
    private IDeviceService service;

    @PostMapping("/create")
    public ResponseEntity<DeviceResponse> createDevice(@Valid @RequestBody DeviceCreateRequest request) {
        DeviceResponse response = service.createDevice(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DeviceResponse>> getDevicesByUserId(@PathVariable String userId) {
        List<DeviceResponse> responseList = service.getDevicesByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

    @GetMapping("/{deviceId}")
    public ResponseEntity<DeviceResponse> getDeviceById(@PathVariable String deviceId) {
        DeviceResponse response = service.getDeviceById(deviceId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{deviceId}/update")
    public ResponseEntity<DeviceResponse> updateDevice(@PathVariable String deviceId, @Valid @RequestBody DeviceUpdateRequest request) {
        DeviceResponse response = service.updateDevice(deviceId, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{deviceId}/delete")
    public ResponseEntity<DeviceResponse> deleteDevice(@PathVariable String deviceId) {
        service.deleteDevice(deviceId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
