package org.shining.crud.service;

import org.shining.crud.model.dto.UserDTO;

public interface UserService {

  UserDTO login(UserDTO user);
  UserDTO findUserByNickname(String nickname);
  UserDTO findUserByEmail(String email);
  boolean signUp(UserDTO user);
}
