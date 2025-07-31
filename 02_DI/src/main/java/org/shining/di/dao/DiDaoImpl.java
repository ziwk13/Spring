package org.shining.di.dao;

import org.springframework.stereotype.Repository;

/*
 * @Repository
 * 
 * 1. 스프링에서 데이터 접근 계층(DAO, Data Access Object)의 클래스를 나타내는 어노테이션
 * 2. 데이터베이스 등 영속성 저장소와의 CRUD(생성, 조회, 수정, 삭제) 작업을 담당하는 계층임을 명시한다.
 * 3. @Component의 특수화 된 어노테이션으로 Spring Container에 빈으로 자동 등록 된다.
 * 4. 데이터 접근 관련 예외(예: SQLException)는 스프링의 DataAccessException으로 변환하여 일관적인 처리가 가능 하도록 한다.
 */

@Repository
public class DiDaoImpl implements DIDao {

  @Override
  public void daoMethod() {
    System.out.println("daoMethod()");
  }

}
