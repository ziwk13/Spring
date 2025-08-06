package org.shining.file.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class AttachDTO {

  private Integer nid;
  private Integer aid;
  private String filePath;
  private String originalFilename;
  private String filesystemName;
}
