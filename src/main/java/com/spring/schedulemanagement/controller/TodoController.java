package com.spring.schedulemanagement.controller;

import com.spring.schedulemanagement.service.TodoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo")
public class TodoController {
    // 속성
    private final TodoService todoService;

    // 생성자
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // 기능
    // ::: 일정 생성 API
    @PostMapping
    public String createTodoAPI() {
        System.out.println("투두 생성 API");
        String data = "데이터";
        String result = todoService.createTodoService(data);
        return result;
    }

    // 일정 조회 API

}