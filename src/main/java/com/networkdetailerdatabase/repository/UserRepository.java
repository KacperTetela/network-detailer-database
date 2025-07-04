package com.networkdetailerdatabase.repository;

import com.networkdetailerdatabase.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByAccessKey(String accessKey);
}
