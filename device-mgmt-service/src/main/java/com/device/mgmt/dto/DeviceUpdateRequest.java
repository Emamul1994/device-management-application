package com.device.mgmt.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceUpdateRequest {
    private String deviceName;
    private String deviceType;
    private String status;
}
