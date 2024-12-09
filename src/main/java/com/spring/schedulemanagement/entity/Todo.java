package com.spring.schedulemanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Todo {
    // 속성
    private Long id;
    private String name;
    private String password;
    private String todo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
