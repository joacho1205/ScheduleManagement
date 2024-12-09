package com.spring.schedulemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
// 클라이언트에게 요청으로 전달받는 데이터
public class TodoRequestDto {
    // 속성
    private String name; // 작성자명
    private String password; // 비밀번호
    private String todo; // 할일
}
