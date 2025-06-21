package com.networkdetailerdatabase.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeviceScan {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @CreationTimestamp
  private Instant scanTime;

  private String hostname;
  private String macAddress;
  private String ipAddress;
  private String cpuManufacture;
  private String cpuName;
  private int cpuGeneration;
  private double cpuGHz;
  private int ramGB;
  private int diskspaceGB;
  private String diskType;
  private String biosVersion;
  private String windowsRequirement;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
}
