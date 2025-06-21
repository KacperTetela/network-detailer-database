package com.networkdetailerdatabase.service;

import com.networkdetailerdatabase.model.DeviceScan;
import com.networkdetailerdatabase.model.DeviceScanDTO;
import com.networkdetailerdatabase.model.User;
import com.networkdetailerdatabase.repository.DeviceScanRepository;
import com.networkdetailerdatabase.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NetworkDetailerService {
  private final DeviceScanRepository deviceScanRepository;
  private final UserRepository userRepository;

  /** Creates a new user and returns accessKey */
  public String registerUser(String username, String password) {
    User user = User.create(username, password);
    userRepository.save(user);
    return user.getAccessKey();
  }

  /** Add new scan if api key is correct */
  public DeviceScan addDeviceScan(String accessKey, DeviceScanDTO deviceScanDTO) {
    User user =
        userRepository
            .findByAccessKey(accessKey)
            .orElseThrow(() -> new IllegalArgumentException("Wrong access key"));

    return deviceScanRepository.save(deviceScanDTO.toEntity(user));
  }

  public List<DeviceScan> getScansForUser(String accessKey) {
    User user =
        userRepository
            .findByAccessKey(accessKey)
            .orElseThrow(() -> new IllegalArgumentException("Wrong access key"));
    return deviceScanRepository.findAllByUserId(user.getId());
  }
}
