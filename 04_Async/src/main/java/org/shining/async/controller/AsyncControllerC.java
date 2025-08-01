package org.shining.async.controller;

import java.util.ArrayList;
import java.util.List;

import org.shining.async.model.dto.BoardDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * org.springframework.http.ResponseEntity
 * 
 * 1. HTTP 응답을 세밀하게 제어하기 위한 클래스
 * 2. HTTP 상태 코드, 응답 헤더, 응답 본문을 포함 할 수 있어 REST API 응답을 명확하고 유연하게 처리 할 수 있다.
 * 3. 생성자 및 빌더 패터을 지원해 다양한 방법으로 코드를 작성 할 수 있다.
 */

@RequestMapping("/c")
@RestController
public class AsyncControllerC {
  
  // 필드
  List<BoardDTO> boards = new ArrayList<>();
  
  // 생성자
  public AsyncControllerC() {
    boards.add(new BoardDTO("제목1", 10));
    boards.add(new BoardDTO("제목2", 40));
    boards.add(new BoardDTO("제목3", 20));
  }
  @RequestMapping(value = {"/detail"}  // 요청 주소 : /c/detail?bid=0
                , produces = "application/json")  // 응답 데이터는 JSON 입니다.
  public ResponseEntity<BoardDTO> methodA(int bid) {
    if(bid < 0 || bid >= boards.size()) {
      return ResponseEntity.notFound().build();  // 빌더 패턴을 이용해서 404 반환
    }
    return ResponseEntity.ok(boards.get(bid));  // 200과 BoardDTO 반환
  }
}
