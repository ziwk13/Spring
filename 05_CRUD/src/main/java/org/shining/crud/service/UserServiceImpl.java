package org.shining.crud.service;

import org.shining.crud.model.dto.UserDTO;
import org.shining.crud.repository.UserDAO;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 
@Service
public class UserServiceImpl implements UserService {

  private final UserDAO userDAO;
  @Override
  public UserDTO findUserByIdEmailAndPassword(UserDTO user) {
    return userDAO.getUser(user);
  }
  @Override
  public UserDTO findUserByNickname(String nickname) {
    return userDAO.getUserByNickname(nickname);
  }

}
