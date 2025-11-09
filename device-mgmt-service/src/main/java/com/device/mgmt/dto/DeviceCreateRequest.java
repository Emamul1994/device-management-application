package com.device.mgmt.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceCreateRequest {

    @NotBlank(message = "Device ID cannot be blank")
    private String deviceId;
    @NotBlank(message = "User ID cannot be blank")
    private String userId;
    private String deviceName;
    private String deviceType;
}
