package org.shining.file.service;

import java.util.List;

import org.shining.file.model.dto.UserDTO;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

  List<UserDTO> findAllUsers();
  UserDTO findUserById(Integer uid);
  boolean signUp(UserDTO user, MultipartFile profile);
}
