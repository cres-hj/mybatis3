package com.example.mybatis3.board;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class BoardController {
  @GetMapping("/board/write")
  public void write() {

  }

//  파일 업로드는 무조건 PostMapping으로 해야해
  @PostMapping("/board/write")
  public ModelAndView write(@ModelAttribute Board board) {
    return new ModelAndView("board/read").addObject("content", board.getContent());
  }
}
