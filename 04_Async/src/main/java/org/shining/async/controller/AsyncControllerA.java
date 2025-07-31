package org.shining.async.controller;

import java.util.ArrayList;
import java.util.List;

import org.shining.async.model.dto.BoardDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * @ResponseBody
 * 
 * 1. 컨트롤러 메소드 레벨의 어노테이션
 * 2. 메소드 반환값을 HTTP 응답 본문(Response Body)으로 직접 쓰도록 한다
 *    JSP 이름이나 새로운 요청 경로로 인식하지 않는다 (ViewResolver 없이 반환값을 처리 한다)
 * 3. JSON, XML 등으로 자동 직렬화 하여 HTTP 응답 본문에 담아 보낸다
 * 4. Jackson 같은 MessageConverter가 자바 객체를 JSON, XML 등으로 변환해서 전달 한다.
 */

@RequestMapping("/a")
@Controller
public class AsyncControllerA {
  
  // 필드
  List<BoardDTO> boards = new ArrayList<>();
  
  // 생성자
  public AsyncControllerA() {
    boards.add(new BoardDTO("제목1", 10));
    boards.add(new BoardDTO("제목2", 40));
    boards.add(new BoardDTO("제목3", 20));
  }
  @ResponseBody
  @RequestMapping(value = {"/list", "/list.json"}
                , produces = "application/json")  // 응답 데이터는 JSON 입니다.
  public List<BoardDTO> methodA() {
    return boards;
  }
  @ResponseBody
  @RequestMapping(value = "/list.xml"
                , produces = "application/xml")  // 응답 데이터는 XML 이다.
  public List<BoardDTO> methodB() {
   return boards; 
  }
}
