package org.shining.ioc.chap01_xml;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

  public static void main(String[] args) {
    
    
    // XML을 이용해 등록한 빈 관리는 GenericXMLApplicationContext 클래스를 이용 한다.
    
    AbstractApplicationContext ctx = new GenericXmlApplicationContext("chap01_xml/appContext.xml");
    
    // Spring Container에 등록한 빈이 singleton인지 확인 하기
    Adder add1 = ctx.getBean("add", Adder.class);
    Adder add2 = ctx.getBean("add", Adder.class);
    System.out.println(add1 == add2);  // true

    // Spring Container에 저장된 빈의 데이터를 확인
    Calculator calculator = ctx.getBean("calc1", Calculator.class);
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
