package com.networkdetailerdatabase.service;

import com.networkdetailerdatabase.exception.InvalidCredentialsException;
import com.networkdetailerdatabase.exception.UserAlreadyExistsException;
import com.networkdetailerdatabase.exception.UserNotFoundException;
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
    if (userRepository.existsByUsername(username)) throw new UserAlreadyExistsException(username);
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

  public List<DeviceScan> getScansForUser(User user) {
    return deviceScanRepository.findAllByUserId(user.getId());
  }

  public User authenticate(String username, String password) {
    User user =
        userRepository
            .findByUsername(username)
            .orElseThrow(() -> new UserNotFoundException(username));

    if (!user.getPassword().equals(password)) {
      throw new InvalidCredentialsException();
    }

    return user;
  }

  public User authenticate(String accessKey) {
    User user =
        userRepository
            .findByAccessKey(accessKey)
            .orElseThrow(() -> new InvalidCredentialsException());
    return user;
  }
}
