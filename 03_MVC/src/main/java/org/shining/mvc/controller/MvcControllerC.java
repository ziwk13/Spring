package org.shining.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.shining.mvc.model.dto.BoardDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/c")
@Controller
public class MvcControllerC {

  // List<BoardDTO> boards
  // 필드
  List<BoardDTO> boards = new ArrayList<BoardDTO>();

  // 생성자 (필드 초기화)
  public MvcControllerC() {
    boards.add(new BoardDTO("제목1", 20));
    boards.add(new BoardDTO("제목2", 10));
    boards.add(new BoardDTO("제목3", 30));
  }

  // ModelAndView를 이용한 forward (데이터 전달)
  @RequestMapping("/list.do")  // 요청 주소 : /c/list.do
  public ModelAndView methodA() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("c/list");         // JSP 이름
    mv.addObject("boards", boards);  // JSP로 전달되는 데이터 boards
    return mv;
  }
  
  /*
   * org.springframework.ui.Model
   * 
   * 1. 컨트롤러가 View(JSP)로 데이터를 전달(forward) 할 때 사용하는 인터페이스
   *    Servlet의 request.setAttribute()와 유사한 역할을 수행 한다.
   * 2. 컨트롤러 메소드의 파라미터로 선언해서 사용한다.
   * 3. Key-Value 형태로 데이터를 저장 한다.
   * 4. 주요 메소드
   *    1) addAttributes(String Key, Object value)
   *    2) addMap()
   * 5. 리다이렉트 시에는 Model에 저장한 데이터는 소멸 된다.
   */

  // Model을 이용한 forward (데이터 전달)
  @RequestMapping("detail.do")  // 요청 주소 : /c//detail.do?bid=1
  public String methodB(Model model  // JSP로 전달 할 데이터를 저장 할 Model
                        , int bid) {  // @RequestParam 생략
    model.addAttribute("board", boards.get(bid));  // JSP로 전달되는 데이터 board
    return "c/detail";  // JSP 이름 (JSP 경로)
  }
  /*
   * org.springframework.web.bind.annotation.ModelAttribute
   * 
   * 1. 컨트롤러 메소드 또는 파라미터에 사용하는 어노테이션
   * 2. Model에 자동으로 데이터를 바인딩하여 View(JSP)에 전달하는 역할을 한다.
   * 3. 용도
   *    1) 컨트롤러 메소드 : 모든 요청 메소드 이전에 먼저 실행 되어서 공통적으로 Model에 데이터를 저장 한다.
   *    2) 파라미터 : 요청 파라미터 (또는 폼 데이터)를 자바 객체에 자동으로 바인딩 한다.
   */
    //@ModelAttribute를 이용한 공통값 처리
    @ModelAttribute("common")
    public String comonAttr() {
    return "공통값";  // 모든 View(JSP)에서 ${common}으로 접근 할 수 있다.
  }
    // @ModelAttribute를 이용한 forward (데이터 전달)
    @RequestMapping("/submit.do")  // 요청 주소 : /c/submit.do?title=제목&hit=10
    public String submit(BoardDTO board) {  // Model에 board라는 이름으로 BoardDTO board 객체가 저장 된다. => JSP에서 ${board}로 확인
      // name = "board"를 생략하면 (@ModelAttribute BoardDTO board) Model에 boardDTO(타입을 이름으로 사용)라는 이름으로 BoardDTO board 객체가 저장 된다. => JSP에서 ${boardDTO} 확인
      // @ModelAttribute를 생략 할 수 있다.(Spring 3.2 이
      return "c/detail";  // JSP 이름(주소)  
    }
  /*
   * org.springframework.web.servlet.mvc.support.RedirectAttributes;
   * 
   * 1. 리다이렉트 시 데이터를 일시적으로 전달하는데 사용하는 인터페이스
   * 2. 리다이렉트시 Model에 저장한 데이터는 소멸되므로(새로운 요청이기 때문에) RedirectAttributes를 사용해야 한다.
   * 3. 주요 메소드
   *    1) addAttributes(String key, Object value)
   *        (1) 전달할 데이터를 쿼리 스트링(/url?Key=value)형태로 넘긴다. (정보가 주소창에 노출 된다)
   *        (2) 새로 고침을 하더라도 데이터가 유지된다.
   *    2) addFlashAttributes(String key, Object value)
   *        (1) 데이터를 내부적으로 세션에 임시로 저장하고, 리다이렉트 이후 딱 한번만 사용 할 수 있다. (세션에서 자동 소멸)
   *        (2) 정보가 주소창에 노출되지 않는다.
   *        (3) 주로 성공/실패 메시지, 경고 메시지 등 임시 정보를 전달 할 때 사용 한다.
   */

  // RedirectAttributes를 이용한 redirect
  @RequestMapping("/regist.do")  // 요청 주소 : /c/regist.do?title=신규제목&hit=0
  public String methodC(RedirectAttributes rAttr
                        , String title
                        , int hit) {
    // 등록
    boolean result = boards.add(new BoardDTO(title, hit));

    // 등록 결과에 따른 메시지
    String msg = result ? "등록 성공" : "등록 실패";

    // 메시지를 전달 (리다이렉트 할 장소로 전달 하는 경우 Flash Attribute 형태로 저장 한다)
    rAttr.addFlashAttribute("msg", msg);

    // redirect (새로운 요청 주소로 리다이렉트 구성)
    return "redirect:/c/list.do";  // 새로운 요청 주소
  }
  // RedirectAttributes를 이용한 redirect
  @RequestMapping("/modify.do")  // 요청 주소 : /c/modify.do?bid=0&title=수정제목&hit=10
  public String methodD(RedirectAttributes redirectAttrs
                      , int bid
                      , String title
                      , int hit) {
   // 수정
    BoardDTO prevBoard = boards.set(bid, new BoardDTO(title, hit));
    
    // 수정 성공 메시지 저장
    redirectAttrs.addFlashAttribute("msg", "수정 성공 " + prevBoard);
    
    // redirect
    return "redirect:/c/detail.do?bid=" + bid;  // 새로운 요청 주소로 리다이렉트
  }
}
