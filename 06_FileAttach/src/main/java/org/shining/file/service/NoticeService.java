package org.shining.file.service;

import java.util.List;
import java.util.Map;

import org.shining.file.model.dto.AttachDTO;
import org.shining.file.model.dto.NoticeDTO;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface NoticeService {

  List<NoticeDTO> findNotices();
  Map<String, Object> findNoticeById(Integer nid);
  boolean addNotice(NoticeDTO notice, List<MultipartFile> files);
  boolean deleteNotice(Integer nid);
  AttachDTO findAttachById(Integer aid);
  Resource loadAttachAsResource(AttachDTO attach);
  
}
