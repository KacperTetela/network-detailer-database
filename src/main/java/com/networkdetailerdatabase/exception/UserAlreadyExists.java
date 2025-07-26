package com.networkdetailerdatabase.exception;

public class UserAlreadyExists extends RuntimeException {

  public UserAlreadyExists(String username) {
    super("User: '" + username + "' already exists");
  }
}
