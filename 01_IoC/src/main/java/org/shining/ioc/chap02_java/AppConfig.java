package org.shining.ioc.chap02_java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * @Configuration
 * 
 * 1. 클래스 레벨 어노테이션으로, 해당 클래스가 스프링 빈 설정 소스임을 나타난다. (나는 Spring Bean Configuration File 입니다.)
 * 2. @Bean 어노테이션이 추가된 메소드 들이 빈을 생성하는 팩토리 역할을 수행 한다.
 * 3. 내부적으로는 싱글톤 보장이 된다.
 * 4. XML <beans> 태그와 동일한 역할을 가진다.
 */
/*
 * @Bean
 * 
 * 1. 메소드 레벨 어노테이션으로, 해당 메소드가 반환하는 Spring Container에 빈으로 등록한다.
 * 2. 메소드 이름이 빈의 아이디가 된다.
 *    @Bean(name = "beanId") 지정하는 것도 가능 한다.
 * 3. XML <bean> 태그와 동일한 역할을 가진다.
 */
@Configuration
public class AppConfig {

  @Bean
  public Adder add() {
    return new Adder();
  }
  
  @Bean
  public Subtractor sub() {
    return new Subtractor();
  }
  @Bean
  public Multiplier mul() {
    return new Multiplier();
  }
  @Bean
  public Divider div() {
    return new Divider();
  }
  @Bean
  public Calculator calc() {
    Calculator calculator = new Calculator();
    calculator.setBrand("소니");
    calculator.setPrice(10000);
    calculator.setAdder(add());  // 다른 빈(Bean) 메소드를 호출해서 빈(Bean) 사이 의존성 주입이 가능 하다
    calculator.setSubtractor(sub());
    calculator.setMultiplier(mul());
    calculator.setDivider(div());
    return calculator;
  }
}
