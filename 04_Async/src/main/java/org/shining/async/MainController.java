package org.shining.async;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

  @RequestMapping(value = "/")
  public String main() {
    return "main";
  }
  
}
