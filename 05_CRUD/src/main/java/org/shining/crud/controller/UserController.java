package org.shining.crud.controller;

import javax.servlet.http.HttpServletRequest;

import org.shining.crud.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequestMapping("/user")
@Controller
@RequiredArgsConstructor
public class UserController {
  
  private final UserService userService;
  
  
  @GetMapping("/login")
  public String loginForm(String redirectURL
                        , HttpServletRequest request
                        , Model model) {
    
    // 로그인 페이지로 이동하기 전에 전달된 redirectURL이 있으면 전달
    if(redirectURL == null || redirectURL.isEmpty()) {  // URL이 없거나 ""빈문자열이면
      // 이전 페이지를 기억 하고 있는 요청 헤더 Referer 참고 (없을 수도 있음)
      String referer = request.getHeader("Referer");
      if(referer == null || referer.isEmpty()) {
        redirectURL = "/";  // main 페이지로 이동
      } else {
        redirectURL = referer;  // referer : 이전 페이지의 주소가 담겨져 있다
      }
    }
    // 로그인 페이지로 redirectURL 전달
    model.addAttribute("redirectURL", redirectURL);
    
    // 로그인 페이지로 forward
    return "user/login";  // JSP 이름
  }
}
