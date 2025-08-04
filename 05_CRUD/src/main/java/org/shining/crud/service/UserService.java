package org.shining.crud.service;

import org.shining.crud.model.dto.UserDTO;

public interface UserService {

  UserDTO findUserByIdEmailAndPassword(UserDTO user);
  UserDTO findUserByNickname(String nickname);
}
