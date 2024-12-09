package com.spring.schedulemanagement.controller;

import com.spring.schedulemanagement.dto.TodoRequestDto;
import com.spring.schedulemanagement.dto.TodoResponseDto;
import com.spring.schedulemanagement.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/todo")
public class TodoController {
    // 속성
    private final TodoService todoService; // Service 계층과 연동

    // 생성자
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // 기능
    //:::일정 생성
    @PostMapping
    public ResponseEntity<TodoResponseDto> createSchedule(@RequestBody TodoRequestDto requestDto) {
        // Service 계층 호출 -> 일정 생성 처리
        TodoResponseDto responseDto = todoService.createSchedule(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    //:::전체 일정 조회
    @GetMapping
    public ResponseEntity<List<TodoResponseDto>> getAllSchedules() {
        // Service 계층 호출 -> 전체 일정 조회
        return ResponseEntity.ok(todoService.getAllSchedules());
    }

    //:::선택 일정 조회
    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDto> getScheduleById(@PathVariable Long id) {
        // Service 계층 호출 -> 선택 일정 조회
        return ResponseEntity.ok(todoService.getScheduleById(id));
    }

    //:::선택 일정 수정
    @PutMapping("/{id}")
    public ResponseEntity<String> updateSchedule(@PathVariable Long id, @RequestBody TodoRequestDto requestDto) {
        // Service 계층 호출 -> 선택 일정 수정 처리
        boolean isUpdated = todoService.updateSchedule(id, requestDto);
        if (!isUpdated) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("비밀번호가 일치하지 않거나 일정이 존재하지 않습니다.");
        }
        return ResponseEntity.ok("일정이 수정되었습니다.");
    }

    //:::선택 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchedule(@PathVariable Long id, @RequestBody Map<String, String> body) {
        // TodoRequestDto클래스에서 비밀번호 추출 후 Service 계층 호출 -> 삭제 처리
        boolean isDeleted = todoService.deleteSchedule(id, body.get("password"));
        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("비밀번호가 일치하지 않거나 일정이 존재하지 않습니다.");
        }
        return ResponseEntity.ok("일정이 삭제되었습니다.");
    }
}
