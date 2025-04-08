package com.example.mybatis3.memo;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import java.util.*;

// 주소 다 걸었는데도 404에러 나면 컨트롤러 안걸었는지 확인
@Controller
public class MemoController {

  // autowired 빼먹어도 dao 생성 실패해도 @service 빼먹어도 null
  // autowired 안넣고 애러냈더니 memoService가 null이다 나오고 들여쓰기 한묶음 나오고 에러가 끝남 밑으로 주루룩 안내려감!
  @Autowired
  private MemoService memoService;

  @GetMapping("/memo/list")
  public ModelAndView findAll() {
    return new ModelAndView("memo/list").addObject("memos", memoService.findAll());
  }

  @GetMapping("/memo/read")
  public ModelAndView findByMemo(@RequestParam int mno) {
    Optional<Memo> result = memoService.findByMemo(mno);
    if(result.isEmpty())
      return new ModelAndView("redirect:/memo/list");
    return new ModelAndView("memo/read").addObject("memo", result.get());
    // if~ 끝까지: 만약 비어있다면 /memo/list로 가라. 있다면 꺼내서 memo/read로 가라
  }

  // 메모 작성하는 페이지: get으로 /memo/write에 오면 write.html 꺼내
  @GetMapping("/memo/write")
  public ModelAndView save() {
    return new ModelAndView("memo/write");
  }

  // post로 /memo/write에 오면 mno 꺼내서 mno의 read 꺼내
  @PostMapping("/memo/write")
  public ModelAndView save(@ModelAttribute Memo memo) {
    int mno = memoService.save(memo);
    return new ModelAndView("redirect:/memo/read?mno=" + mno);
  }

  @PostMapping("/memo/update")
  public ModelAndView update(@ModelAttribute Memo memo) {
    memoService.update(memo);
    return new ModelAndView("redirect:/memo/read?mno=" + memo.getMno());
  }

  @PostMapping("/memo/delete")
  public ModelAndView delete(@RequestParam Integer mno) {
    memoService.delete(mno);
    return new ModelAndView("redirect:/memo/list");
  }
}
