package com.spring.schedulemanagement.service;

import com.spring.schedulemanagement.repository.TodoRepository;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    // 속성
    private final TodoRepository todoRepository;

    // 생성자
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // 기능
    // ::: 일정 생성 서비스
    public String createTodoService(String data) {
        // 비즈니스로직
        System.out.println(data);
        String result = todoRepository.createTodo(data);
        return result;
    }

    // 일정 조회 서비스 메서드 만들기
}
