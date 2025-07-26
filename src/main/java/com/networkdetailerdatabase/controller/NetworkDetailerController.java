package com.networkdetailerdatabase.controller;

import com.networkdetailerdatabase.model.DeviceScanDTO;
import com.networkdetailerdatabase.model.User;
import com.networkdetailerdatabase.service.NetworkDetailerService;
import com.networkdetailerdatabase.model.DeviceScan;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class NetworkDetailerController {
  private final NetworkDetailerService networkDetailerService;

  /** Registers a user, returns his accessKey */
  @PostMapping("/register")
  public ResponseEntity<String> register(
      @RequestParam String username, @RequestParam String password) {
    String accessKey = networkDetailerService.registerUser(username, password);
    return ResponseEntity.ok(accessKey);
  }

  /** Adds a new scan - requires accessKey in header or parameter */
  @PostMapping("/scans")
  public ResponseEntity<DeviceScanDTO> addScan(
      @RequestHeader("X-ACCESS-KEY") String accessKey, @RequestBody DeviceScanDTO deviceScanDto) {

    DeviceScan saved = networkDetailerService.addDeviceScan(accessKey, deviceScanDto);
    return ResponseEntity.ok(DeviceScanDTO.toDTO(saved));
  }

  /** Downloads all scans for a given accessKey */
  @GetMapping("/scans/by-key")
  public ResponseEntity<List<DeviceScanDTO>> getScansByAccessKey(
      @RequestHeader("X-ACCESS-KEY") String accessKey) {

    User user = networkDetailerService.authenticate(accessKey);

    List<DeviceScan> scans = networkDetailerService.getScansForUser(user);

    List<DeviceScanDTO> dtoList = scans.stream().map(DeviceScanDTO::toDTO).toList();

    return ResponseEntity.ok(dtoList);
  }

  /** Downloads all scans for a given user access */
  @GetMapping("/scans/by-credentials")
  public ResponseEntity<List<DeviceScanDTO>> getScansByCredentials(
      @RequestParam String username, @RequestParam String password) {

    User user = networkDetailerService.authenticate(username, password);

    List<DeviceScan> scans = networkDetailerService.getScansForUser(user);

    List<DeviceScanDTO> dtoList = scans.stream().map(DeviceScanDTO::toDTO).toList();

    //return ResponseEntity.ok(dtoList);
      return null;
  }
}
