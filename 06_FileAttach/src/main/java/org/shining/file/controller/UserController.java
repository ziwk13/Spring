package org.shining.file.controller;

import org.shining.file.model.dto.UserDTO;
import org.shining.file.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
public class UserController {
  
  private final UserService userService;
  
  @GetMapping("/list")
  public String list(Model model) {
    model.addAttribute("users", userService.findAllUsers());
    return "user/list";
  }
  @PostMapping("/signup")
  public String signup(UserDTO user
                     , @RequestParam("profile") MultipartFile profile
                     , RedirectAttributes redirectAttr) {
    // 회원 가입
    boolean signupResult = userService.signUp(user, profile);
    // 회원 가입 실패
    if(!signupResult) {
     redirectAttr.addFlashAttribute("error", "회원 가입 실패함 다시 하셈");
     return "redirect:/user/list";  // 회원 가입 화면으로 redirect 하기
    }
    // 회원 가입 성공
    redirectAttr.addFlashAttribute("msg", "축하 가입 되었음");
    return "redirect:/";  // 메인 화면으로 redirect 하기
  }
  @GetMapping("/detail")
  public String detail(Integer uid
                     , Model model) {
    model.addAttribute("user", userService.findUserById(uid));
    return "user/detail";
  }
}
