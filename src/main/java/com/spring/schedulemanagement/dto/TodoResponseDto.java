package com.spring.schedulemanagement.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoResponseDto {
    private Long id;
    private String name;
    private String todo;
    private LocalDateTime updatedAt;
}
