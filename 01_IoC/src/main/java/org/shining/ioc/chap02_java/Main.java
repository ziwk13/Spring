package org.shining.ioc.chap02_java;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class Main {

  public static void main(String[] args) {
    
    
    // XML을 이용해 등록한 빈 관리는 AnnotationConfigApplicationContext 클래스를 이용 한다.
    
    AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    
    // Spring Container에 등록한 빈이 singleton인지 확인 하기
    Adder add1 = ctx.getBean("add", Adder.class);
    Adder add2 = ctx.getBean("add", Adder.class);
    System.out.println(add1 == add2);  // true

    // Spring Container에 저장된 빈의 데이터를 확인
    Calculator calculator = ctx.getBean("calc", Calculator.class);
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
