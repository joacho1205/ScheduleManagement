package com.spring.schedulemanagement.service;

import com.spring.schedulemanagement.dto.TodoRequestDto;
import com.spring.schedulemanagement.dto.TodoResponseDto;
import com.spring.schedulemanagement.entity.Todo;
import com.spring.schedulemanagement.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    // 속성
    private final TodoRepository todoRepository; // Repository 계층과 연동

    // 생성자
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // 기능
    // :::일정 생성
    public TodoResponseDto createSchedule(TodoRequestDto requestDto) {
        // 요청 데이터를 Todo 객체로 변환
        Todo todo = new Todo(
                null,
                requestDto.getName(),
                requestDto.getPassword(),
                requestDto.getTodo(),
                LocalDateTime.now(), // 작성 시간
                LocalDateTime.now() // 수정 시간 (초기값)
        );
        // 저장소에 저장
        Todo savedTodo = todoRepository.save(todo);
        // 저장된 데이터를 TodoResponseDto로 변환
        return new TodoResponseDto(savedTodo.getId(), savedTodo.getName(), savedTodo.getTodo(), savedTodo.getUpdatedAt());
    }

    // :::전체 일정 조회
    public List<TodoResponseDto> getAllSchedules() {
        List<TodoResponseDto> responseDtos = new ArrayList<>();
        List<Todo> todos = todoRepository.findAll();
        // 조회된 데이터를 DTO 리스트로 변환
        for (Todo todo : todos) {
            responseDtos.add(new TodoResponseDto(
                    todo.getId(),
                    todo.getName(),
                    todo.getTodo(),
                    todo.getUpdatedAt())
            );
        }
        return responseDtos;
    }

    // :::선택 일정 조회
    public TodoResponseDto getScheduleById(Long id) {
        // ID로 일정 검색
        Todo todo = todoRepository.findById(id);
        if (todo == null) {
            throw new IllegalArgumentException("일정을 찾을 수 없습니다.");
        }
        // 검색 결과를 DTO로 변환
        return new TodoResponseDto(todo.getId(), todo.getName(), todo.getTodo(), todo.getUpdatedAt());
    }

    // :::선택 일정 수정
    public boolean updateSchedule(Long id, TodoRequestDto requestDto) {
        // 저장소에 수정 요청
        return todoRepository.update(id, requestDto.getTodo(), requestDto.getName(), requestDto.getPassword());
    }

    // :::선택 일정 삭제
    public boolean deleteSchedule(Long id, String password) {
        // 저장소에 삭제 요청
        return todoRepository.delete(id, password);
    }
}
