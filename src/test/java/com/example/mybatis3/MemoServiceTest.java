package com.example.mybatis3;

import com.example.mybatis3.memo.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MemoServiceTest {
  @Autowired
  private MemoService memoService;

  // @Test
  public void saveTest() {
    Memo memo = new Memo(null, "연습", "연습 연습", "spring", null);
    memoService.save(memo);
    System.out.println(memo);
  }

  // @Test
  public void findAllTest() {
    List<Memo> memos = memoService.findAll();
    System.out.println(memos);
    assertNotEquals(0, memos.size());
  }

  // @Test
  public void findByMnoTest() {
    assertEquals(true, memoService.findByMemo(7).isPresent());
    // memoService에서 7번 글을 불러오면 값이 있을거야(isPresent()).
    // 값이 있으면 .isPresent() 값이 true야. assertEquals로 true인지 비교
  }

  // @Test
  public void updateTest() {
    Memo memo = new Memo(8, "변경두번째", "변경된거야", null, null);
    memoService.update(memo);
    assertEquals(true, memoService.update(memo));
    // dao에서 true/false로 나오게 했으니까 assertEquals에도 true/false로 써줘야해!
    // 1로 적었다가 테스트는 실패했다고 뜨는데 행은 변경되어서 뭐지~ 했어
  }

  @Transactional
  @Test
  public void deleteTest() {
    assertEquals(true, memoService.delete(8));
  }
}
