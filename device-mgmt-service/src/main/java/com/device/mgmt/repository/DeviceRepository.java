package com.device.mgmt.repository;

import com.device.mgmt.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    List<Device> findByUserId(String userId);
    Optional<Device> findByDeviceId(String deviceId);
    void deleteByDeviceId(String deviceId);
    boolean existsByDeviceId(String deviceId);
}
