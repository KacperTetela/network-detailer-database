package com.networkdetailerdatabase.model;

import java.time.Instant;

public record DeviceScanDTO(
    Long id,
    Instant scanTime,

    String hostname,
    String macAddress,
    String ipAddress,
    String cpuManufacture,
    String cpuName,
    int cpuGeneration,
    double cpuGHz,
    int ramGB,
    int diskspaceGB,
    String diskType,
    String biosVersion,
    String windowsRequirements,

    String username,
    String accessKey) {

  public static DeviceScanDTO toDTO(DeviceScan deviceScan) {
    return new DeviceScanDTO(
        deviceScan.getId(),
        deviceScan.getScanTime(),

        deviceScan.getHostname(),
        deviceScan.getMacAddress(),
        deviceScan.getIpAddress(),
        deviceScan.getCpuManufacture(),
        deviceScan.getCpuName(),
        deviceScan.getCpuGeneration(),
        deviceScan.getCpuGHz(),
        deviceScan.getRamGB(),
        deviceScan.getDiskspaceGB(),
        deviceScan.getDiskType(),
        deviceScan.getBiosVersion(),
        deviceScan.getWindowsRequirement(),

        deviceScan.getUser().getUsername(),
        deviceScan.getUser().getAccessKey());
  }

  public DeviceScan toEntity(User user) {
    DeviceScan scan = new DeviceScan();
    scan.setScanTime(scanTime);
    scan.setHostname(hostname);
    scan.setMacAddress(macAddress);
    scan.setIpAddress(ipAddress);

    scan.setCpuManufacture(cpuManufacture);
    scan.setCpuName(cpuName);
    scan.setCpuGeneration(cpuGeneration);
    scan.setCpuGHz(cpuGHz);
    scan.setRamGB(ramGB);
    scan.setDiskspaceGB(diskspaceGB);
    scan.setDiskType(diskType);
    scan.setBiosVersion(biosVersion);
    scan.setWindowsRequirement(windowsRequirements);

    scan.setUser(user);
    return scan;
  }
}
