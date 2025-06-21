package com.networkdetailerdatabase.repository;

import com.networkdetailerdatabase.model.DeviceScan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceScanRepository extends JpaRepository<DeviceScan, Long> {
  List<DeviceScan> findAllByUserId(Long userId);
}
