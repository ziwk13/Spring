package org.shining.file.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.shining.file.model.dto.AttachDTO;
import org.shining.file.model.dto.NoticeDTO;
import org.shining.file.repository.NoticeDAO;
import org.shining.file.util.FileUtil;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Transactional  // 서비스 클래스 레벨에 설정한 @Transactional에 의해서 모든 메소드는 트랜잭션 처리가 된다.
@RequiredArgsConstructor
@Service
public class NoticeServiceImpl implements NoticeService {

  private final NoticeDAO noticeDAO;
  private final FileUtil fileUtil;
  
  @Transactional(readOnly = true)  // 읽기 전용 최적화를 통해 트랜잭션 매니저의 불필요한 동작을 방지하여 성능 향상할 수 있다.
  @Override
  public List<NoticeDTO> findNotices() {
    return noticeDAO.getNotices();
  }

  @Transactional(readOnly = true)  // 읽기 전용 최적화를 통해 트랜잭션 매니저의 불필요한 동작을 방지하여 성능 향상할 수 있다.
  @Override
  public Map<String, Object> findNoticeById(Integer nid) {
    return Map.of("notice", noticeDAO.getNoticeById(nid)
                , "attaches", noticeDAO.getAttaches(nid));
  }

  @Override
  public boolean addNotice(NoticeDTO notice, List<MultipartFile> files) {
    try {
      // 공지사항 DB에 등록하기
      System.out.println("공지사항 등록 이전 nid : " + notice.getNid());
      int addedNoticeCount = noticeDAO.insertNotice(notice);  // // INSERT 수행하면서 파라미터 notice의 nid 필드에 자동 생성된 PK 값이 저장된다.
      System.out.println("공지사항 등록 이후 nid : " + notice.getNid());  
      
      if(addedNoticeCount == 1) {
        // 첨부파일 서버에 저장하기
        for(MultipartFile file : files) {
          if(!file.isEmpty()) {
            // 첨부 파일을 저장 할 경로
            String filePath = fileUtil.getFilePath();
            // 디렉터리 없으면 생성하기
            Path uploadPath = Paths.get(filePath);
            if(Files.notExists(uploadPath)) {
              Files.createDirectories(uploadPath);
            }
            // 첨부 파일의 원래 이름
            String originalFilename = file.getOriginalFilename();
            // 첨부 파일을 서버에 저장 할 때 사용 하는 이름
            String filesystemName = fileUtil.getFilesystemName(originalFilename);
            // 첨부 파일을 서버에 저장
            file.transferTo(Paths.get(filePath + "/" + filesystemName));
            // 첨부파일 DB에 등록하기
            AttachDTO attach = AttachDTO.builder()
                .nid(notice.getNid())
                .filePath(filePath)
                .originalFilename(originalFilename)
                .filesystemName(filesystemName)
                .build();
            int addedAttachResult = noticeDAO.insertAttach(attach);
            if(addedAttachResult == 0) {
              return false;
            }
          } 
        }
        return true;
      } else {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean deleteNotice(Integer nid) {
    // 삭제 하려는 공지사항에 등록된 첨부 목록 가져오기
    List<AttachDTO> attaches = noticeDAO.getAttaches(nid);
    // 첨부 목록 참조해서 서버에 저장된 첨부 파일들을 삭제하기
    attaches.stream()
        .map(attach -> new File(attach.getFilePath(), attach.getFilesystemName()))
        .filter(file -> file.exists())  // 메소드 참조 방식 .filter(File::exists)
        .forEach(file -> {
          boolean deleted = file.delete();
          if(!deleted) {
            System.out.println("파일 삭제 실패 : " + file.getPath());
          }
        });
    /*
    for(AttachDTO attach : attaches) {
      String path = attach.getFilePath() + "/" + attach.getFilesystemName();
      File file = new File(path);
      if(file.exists()) {
        boolean deleted = file.delete();
        if(!deleted) {
          System.out.println("파일 삭제 실패 : " + file.getPath());
        }
      }
    }
    */
    // DB에서 공지사항 삭제하기 (ON DELETE CASCADE에 의해서 첨부 목록은 함께 삭제된다.
    return noticeDAO.deleteNoticeById(nid) == 1;
  }

  @Transactional(readOnly = true)  // 읽기 전용 최적화를 통해 트랜잭션 매니저의 불필요한 동작을 방지하여 성능 향상할 수 있다.
  @Override
  public AttachDTO findAttachById(Integer aid) {
    return noticeDAO.getAttachById(aid);
  }
  @Override
  public Resource loadAttachAsResource(AttachDTO attach) {
    return new FileSystemResource(attach.getFilePath() + "/" + attach.getFilesystemName());
  }
}
