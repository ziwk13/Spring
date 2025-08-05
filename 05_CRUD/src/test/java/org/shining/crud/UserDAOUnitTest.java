package org.shining.crud;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.shining.crud.model.dto.UserDTO;
import org.shining.crud.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  // JUnit4를 이용한 테스트
@ContextConfiguration(locations = {  // Test 시 필요한 빈(Bean)을 찾을 장소를 작성 한다 (root-context.xml의 4개 빈과 UserDAO 빈)
    "file:src/main/webapp/WEB-INF/spring/root-context.xml"               // root-context.xml
  , "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" // @Repository는 component-scan이 있어야 동작하므로 추가
})
public class UserDAOUnitTest {

  @Autowired  // 테스트 코드는 DI를 필드 주입 방식으로 처리 한다.
  private UserDAO userDAO;
  @Test
  public void 사용자_가져오기_테스트() {
   // assertNotNull(userDAO.getUser(new UserDTO(0, "shark@gmail.com", "shark", null)));
  }

}
