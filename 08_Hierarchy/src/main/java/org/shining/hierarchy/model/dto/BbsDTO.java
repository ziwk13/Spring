package org.shining.hierarchy.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BbsDTO {

  private Integer bbsId;
  private String content;
  private Integer state;
  private Integer depth;
  private Integer groupId;
  private Integer groupOrder;
}
