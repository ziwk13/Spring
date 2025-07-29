package org.shining.ioc.chap01_xml;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor  // Calculator() : 디폴트 생성자
@AllArgsConstructor  // 모든 매개변수가 들어간 생성자
@Getter
@Setter
@ToString

public class Calculator {

  private String brand;
  private int price;
  private Adder adder;
  private Subtractor subtractor;
  private Multiplier multiplier;
  private Divider divider;
}
