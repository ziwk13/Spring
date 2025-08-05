package org.shining.file.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.shining.file.model.dto.UserDTO;
import org.shining.file.repository.UserDAO;
import org.shining.file.util.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
  
  private final UserDAO userDAO;
  private final FileUtil fileUtil;

  @Override
  public List<UserDTO> findAllUsers() {
    return userDAO.getAllUsers();
  }

  @Override
  public UserDTO findUserById(Integer uid) {
    return userDAO.getUserById(uid);
  }

  @Override
  public boolean signUp(UserDTO user, MultipartFile profile) {
    try {
      
      // 첨부 파일을 저장 할 경로
      String filePath = fileUtil.getFilePath();
      
      // 디렉터리 없으면 생성 하기
      Path uploadPath = Paths.get(filePath);
      if(Files.notExists(uploadPath)) {
        Files.createDirectories(uploadPath);
      }
      
      // 첨부 파일의 원래 이름
      String originalFilename = profile.getOriginalFilename();
      
      // 첨부 파일을 서버에 저장 할 때 사용 하는 이름
      String filesystemName = fileUtil.getFilesystemName(originalFilename);
      
      // 첨부 파일을 서버에 저장
      Path path = Paths.get(filePath + "/" + filesystemName);
      Files.copy(profile.getInputStream(), path);
      
      
      // DB에 정보 저장 하기
      user.setFilePath(filePath);
      user.setOriginalFilename(originalFilename);
      user.setFilesystemName(filesystemName);
      
      return userDAO.insertUser(user) == 1;  // 회원 가입 성공 여부
    } catch (Exception e) {
      e.printStackTrace();
      return false;  // 회원 가입 실패
    }
  }

}
