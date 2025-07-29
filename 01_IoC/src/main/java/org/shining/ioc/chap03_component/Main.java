package org.shining.ioc.chap03_component;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class Main {

  public static void main(String[] args) {
    
    
    // @Component을 이용해 등록한 빈 관리는 AnnotationConfigApplicationContext 클래스를 이용 한다.
    
    AbstractApplicationContext ctx = new AnnotationConfigApplicationContext("org.shining.ioc.chap03_component");
    
    // Spring Container에 저장된 빈의 데이터를 확인
    Calculator calculator = ctx.getBean("calculator", Calculator.class);
    System.out.println(calculator.getBrand());
    System.out.println(calculator.getPrice());
    System.out.println(calculator.getAdder().add(1, 2, 3, 4, 5));
    System.out.println(calculator.getSubtractor().subtract(3, 5));
    System.out.println(calculator.getMultiplier().multiply(1, 2, 3, 4, 5));
    System.out.println(calculator.getDivider().divide(7, 3));
    
     // 자원 반납 (생략 가능)
    ctx.close();
    
  }

}
