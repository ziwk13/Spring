package org.shining.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/a")
@Controller
public class MvcControllerA {
  
  /*
   * @RequestMapping
   * 
   * 1. 스프링 MVC에서 웹 요청(HTTP 요청)을 특정 컨트롤러 클래스 또는 특정 컨트롤러 메소드에 매핑 할 때 사용하는 어노테이션
   * 2. 클라이언트의 요청 주소와 요청 메소드(GET, POST 등)를 컨트롤러의 메소드와 연결하는 역할을 주로 수행 한다.
   * 3. 클래스 레벨과 메소드 레벨 모두에서 사용 가능 하다.
   *    1) 클래스 레벨 : 공통되는 요청 주소(예: /board/*)지정
   *    2) 메소드 레벨 : 세부 요청 주소(예: /board/list, /board/detail 등)를 지정
   * 4. 스프링 4.3 부터는 HTTP 메소드별로 간결한 축약 어노테이션을 제공 한다. (예: @GetMapping, @PostMapping 등)
   */

  @RequestMapping(value = "/list", method = RequestMethod.GET)  // GET 방식의 경우 method를 생략 할 수 있다.
  public String methodA() {
    System.out.println("methodA()");
    return "a/list";
    /*
     * return "a/list"; 처리 과정
     * 
     * 1. 반환값 "a/list"는 ViewResolver에게 전달 된다. (ViewResolver는 servlet-context.xml에서 확인 할 수 있다)
     * 2. ViewResolver는 prefix 값을 반환값 앞에 추가 하고, suffix 값을 반환값 뒤에 추가 한다.
     *    "/WEB-INF/views/" + "a/list" + ".jsp" -> "/WEB-INF/views/a/list.jsp"
     * 3. 해당 View(JSP)로 forward 한다.
     */
  }
  
  @RequestMapping("/detail")  // value만 작성하는 경우 "value = " 생략 할 수 있다.
  public void methodB() {
    System.out.println("methodB()");
    /*
     * return이 없는 경우 처리 과정
     * 
     * 1. 요청 주소를 반환값으로 해석 한다. (즉 "/a/detail" 값을 반환값으로 해석 한다.)
     * 2. ViewResolver는 prefix 값을 반환값 앞에 추가 하고, suffix 값을 반환값 뒤에 추가 한다.
     *    "/WEB-INF/views/" + "a/list" + ".jsp" -> "/WEB-INF/views/a/detail.jsp"
     * 3. 해당 View(JSP)로 forward 한다.
     */
  }
  
  @RequestMapping("/b/c/d/regist")  // 요청 주소(/a/b/c/d/regist)와 프로젝트 내 경로(a/regist)를 다르게 지정하면 보안에 도움이 된다.
  public ModelAndView methodC() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("a/regist");
    return mv;
    /*
     * ModelAndView 객체를 반환하는 경우
     * 
     * 1. ModelAndView 객체의 viewName 필드에 저장된 값을 forward 할 JSP 경로로 인식 한다.
     * 2. HTTP 상태 코드, forward 할 데이터 등을 함께 객체에 담아 처리 할 수 있다.
     */
  }
}
