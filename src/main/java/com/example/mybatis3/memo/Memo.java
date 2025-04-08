package com.example.mybatis3.memo;

import lombok.*;
import org.springframework.format.annotation.*;

import java.time.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class Memo {
  private Integer mno;
  private String title;
  private String content;
  private String writer;
  // <input type='date>로 날짜를 선택하면 서버로 '2025-10-20' 형식의 문자열이 넘어가
  // 스프링에서 문자열을 날짜로 변환 => 어노테이션을 추가해 변환
  @DateTimeFormat(pattern = "yy-MM-dd")
  private LocalDate regDate;
}
