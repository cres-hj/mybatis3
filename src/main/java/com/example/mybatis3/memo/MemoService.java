package com.example.mybatis3.memo;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class MemoService {
  @Autowired
  private MemoDao memoDao;

  public int save(Memo memo) {
    // 현재 memo의 mno는 값이 없다

    // DAO에서 @SelectKey를 이용해서 memo의 mno에 시퀀스 값을 저장
    memoDao.save(memo);

    // dao 호출이 끝나고 나면, dao가 생성해서 저장한 값이 memo에 들어있어
    return memo.getMno();
  }

  public List<Memo> findAll() {
    return memoDao.findAll();
  }

  public Optional<Memo> findByMemo(int mno) {
    return memoDao .findByMno(mno);
  }

  public boolean update(Memo memo) {
    return memoDao.update(memo)>0;
    // 업데이트 실패하면 0 업데이트 성공하면 1 이상의 숫자겠지
    // 그걸 boolean 타입으로 바꿔주는거야
  }

  public boolean delete(int mno) {
    return memoDao.delete(mno)>0;
  }
}
