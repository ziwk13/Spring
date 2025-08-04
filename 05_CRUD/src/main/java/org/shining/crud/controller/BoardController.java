package org.shining.crud.controller;

import javax.servlet.http.HttpSession;

import org.shining.crud.model.dto.BoardDTO;
import org.shining.crud.model.dto.UserDTO;
import org.shining.crud.service.BoardService;
import org.shining.crud.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/board")
@Controller
public class BoardController {

  private final BoardService boardService;
  private final UserService userService;
  
  @GetMapping("/list")
  public String list(Model model) {  // Model : JSP로 전달할 데이터를 저장하는 컨테이너
    model.addAttribute("boardCount", boardService.getBoardCount());
    model.addAttribute("boards", boardService.findAllBoards());
    return "board/list";  // board 폴더 아래 list.jsp로 forward 한다.
  }
  @GetMapping("/write")
  public String writeForm() {
    return "board/write";  // board 폴더 아래 write.jsp로 forward 한다.
  }
  @PostMapping("/write")
  public String write(HttpSession session                   // 세션을 사용하고 싶은 경우 컨트롤러 메소드의 매개변수로 선언하면 된다.
                       , RedirectAttributes redirectAttrs  // redirect 시 사용하는 데이터 전달 컨테이너
                       , BoardDTO board) {                // 객체 board에는 write.jsp에서 제출한 title과 content가 저장 된다.
    // 객체 board에 작성자 정보 uid를 추가 해야 한다.
    // 작성자 정보는 현재 세션에 nickname이 존재 한다.
    // 따라서, nickname에 해당하는 작성자의 uid를 DB로 부터 가져와야 한다.
    
    String nickname = (String)session.getAttribute("nickname");
    UserDTO user = userService.findUserByNickname(nickname);
    board.setUser(user);
    // 등록
    boolean result = boardService.addBoard(board);
    // 등록 후 목록 보기 요청을 redirect
    // redirect 시 데이터 전달은 Model을 이용 할 수 없다.
    redirectAttrs.addFlashAttribute("msg", result ? "등록 성공" : "등록 실패");
    // redirect 시 데이터 전달은 RedirectAttributes를 이용 할 수 있다.
    return "redirect:/board/list";  // @GetMapping("/board/list") 으로  요청 한다.
  }
  @GetMapping("/detail")
  public String detail(Integer bid  // bid에 해당하는 board 정보를 가져와서
                      , Model model) {  // Model에 저장한 후 board 폴더 아래 detail.jsp로 forward 한다.
    BoardDTO board = boardService.findBoardById(bid);
    model.addAttribute("board", board);
    return "board/detail";
  }
  @GetMapping("/modify")
  public String modifyForm(Integer bid
                          , Model model) {    
    BoardDTO board = boardService.findBoardById(bid);
    model.addAttribute("board", board);
    return "board/modify";
  }
  @PostMapping("/modify")
  public String modify(BoardDTO board  // title, content, bid를 객체 board가 저장 한다.
                      , RedirectAttributes redirectAttr) {  
    // 수정
    boolean result = boardService.modifyBoard(board);
    // 전달 할 메시지 저장
    redirectAttr.addFlashAttribute("msg", result ? "수정 완료" : "수정 실패")  // Flash Attribute : redirect 하는 곳으로 보내는 데이터를 저장 할 때 사용 한다.
                .addAttribute("bid", board.getBid());  //                         Attribute : redirect 할 요청 주소에 요청 파라미터를 추가 할 때 사용 한다.
    // 상세 보기 요청으로 redirect
    return "redirect:/board/detail?bid={bid}";  // redirectAttr.addAttribute("bid", board.getBid())에 의해서
                                                // "redirect:/board/detail?bid=1" 형식으로 요청 된다.
  }
  @GetMapping("/delete")
  public String delete(Integer bid
                       , RedirectAttributes redirectAttr) {
    // 삭제
    boolean result = boardService.removeBoard(bid);
    // 전달 할 메시지 작성
    redirectAttr.addFlashAttribute("msg", result ? "삭제 완료" : "삭제 실패");
    // 목록 보기 요청으로 redirect
    return "redirect:/board/list";
  }
}
