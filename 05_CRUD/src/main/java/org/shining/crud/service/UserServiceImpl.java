package org.shining.crud.service;

import org.shining.crud.model.dto.UserDTO;
import org.shining.crud.repository.UserDAO;
import org.shining.crud.util.java.SecureUtil;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 
@Service
public class UserServiceImpl implements UserService {

  private final UserDAO userDAO;
  private final SecureUtil secureUtil;
  
  @Override
  public UserDTO login(UserDTO user) {
    // email이 일치하는 사용자 조회하기
    UserDTO foundUser = userDAO.getUserByEmail(user.getEmail());
    if(foundUser == null) {
      return null;  // 존재 하지 않는 이메일(아이디)
    }
    // 사용자가 입력한 비밀번호와 DB에 저장된 salt를 이용해 회원가입 당시와 같은 방식으로 비밀번호 암호화 하기
    String password = user.getPassword();
    byte[] salt = foundUser.getSalt();
    String encryptedPassword = secureUtil.hashPBKDF2(password, salt);
    // DB에서 가져온 비밀번호와 암호화한 비밀번호 비교한 결과를 반환 하기
    return encryptedPassword.equals(foundUser.getPassword()) ? foundUser : null;
  }
  @Override
  public UserDTO findUserByNickname(String nickname) {
    return userDAO.getUserByNickname(nickname);
  }
  @Override
  public UserDTO findUserByEmail(String email) {
    return userDAO.getUserByEmail(email);
  }
  @Override
  public boolean signUp(UserDTO user) {
    // salt 생성
    byte[] salt = secureUtil.getSalt();
    // 비밀번호 암호화 하기
    String encryptedPassword = secureUtil.hashPBKDF2(user.getPassword(), salt);
    // DB로 보낼 salt, 암호화 된 비밀번호를 UserDTO에 저장
    user.setSalt(salt);
    user.setPassword(encryptedPassword);
    // 회원가입 후 결과 반환
    return userDAO.insertUser(user) == 1;
  }
}
