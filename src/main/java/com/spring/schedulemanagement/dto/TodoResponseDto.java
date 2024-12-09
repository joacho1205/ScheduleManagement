package com.spring.schedulemanagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
// 클라이언트에게 응답으로 전달하는 데이터
public class TodoResponseDto {
    // 속성
    private Long id; // 일정 ID (고유값)
    private String name; // 작성자명
    private String todo; // 할일
    private LocalDateTime updatedAt; // 마지막 수정일

    // 생성자
    public TodoResponseDto(Long id, String name, String todo, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.todo = todo;
        this.updatedAt = updatedAt;
    }
}
