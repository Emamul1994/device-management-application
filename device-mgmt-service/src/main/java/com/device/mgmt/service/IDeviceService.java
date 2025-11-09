package com.device.mgmt.service;

import com.device.mgmt.dto.DeviceCreateRequest;
import com.device.mgmt.dto.DeviceResponse;
import com.device.mgmt.dto.DeviceUpdateRequest;

import java.util.List;

public interface IDeviceService {
    public DeviceResponse createDevice(DeviceCreateRequest request);
    List<DeviceResponse> getDevicesByUserId(String userId);
    public DeviceResponse getDeviceById(String deviceId);
    public DeviceResponse updateDevice(String deviceId, DeviceUpdateRequest request);
    public void deleteDevice(String deviceId);
}
