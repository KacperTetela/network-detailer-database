package com.networkdetailerdatabase.model;

import java.time.Instant;

public record DeviceScanDTO(
    Long id,
    Instant scanTime,
    String hostname,
    String macAddress,
    String ipAddress,
    String username,
    String accessKey) {

  public static DeviceScanDTO toDTO(DeviceScan deviceScan) {
    return new DeviceScanDTO(
        deviceScan.getId(),
        deviceScan.getScanTime(),
        deviceScan.getHostname(),
        deviceScan.getMacAddress(),
        deviceScan.getIpAddress(),
        deviceScan.getUser().getUsername(),
        deviceScan.getUser().getAccessKey());
  }

  public DeviceScan toEntity(User user) {
    DeviceScan scan = new DeviceScan();
    scan.setScanTime(scanTime);
    scan.setHostname(hostname);
    scan.setMacAddress(macAddress);
    scan.setIpAddress(ipAddress);
    scan.setUser(user);
    return scan;
  }
}
