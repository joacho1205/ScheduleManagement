package com.spring.schedulemanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
// 일정 데이터를 담는 클래스 Todo
public class Todo {
    // 속성
    private Long id; // 일정 ID (고유값)
    private String name; // 작성자명
    private String password; // 비밀번호
    private String todo; // 할일
    private LocalDateTime createdAt; // 작성일
    private LocalDateTime updatedAt; // 수정일
}