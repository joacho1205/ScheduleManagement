package com.spring.schedulemanagement.repository;

import com.spring.schedulemanagement.entity.Todo;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TodoRepository {
    // 속성
    private final List<Todo> todos = new ArrayList<>(); // 메모리 저장소
    private Long nextId = 1L; // 고유 ID 생성기

    // 생성자

    // 기능
    // :::일정 생성
    public Todo save(Todo todo) {
        todo.setId(nextId++); // 고유 ID 할당
        todos.add(todo); // 저장소에 추가
        return todo;
    }

    // :::전체 일정 조회
    public List<Todo> findAll() {
        return new ArrayList<>(todos); // 저장소 복사본 반환
    }

    // :::선택 일정 조회
    public Todo findById(Long id) {
        for (Todo todo : todos) {
            if (todo.getId().equals(id)) {
                return todo; // 일치하는 일정 반환
            }
        }
        return null; // 없으면 null 반환
    }

    // :::선택 일정 수정
    public boolean update(Long id, String todo, String name, String password) {
        Todo existingTodo = findById(id);
        if (existingTodo != null) { // 일정 존재 여부 확인
            if (existingTodo.getPassword().equals(password)) { // 비밀번호 확인
                existingTodo.setTodo(todo);
                existingTodo.setName(name);
                existingTodo.setUpdatedAt(LocalDateTime.now()); // 수정 시간 갱신
                return true; // 수정 성공
            }
        }
        return false; // 수정 실패
    }

    // :::선택 일정 삭제
    public boolean delete(Long id, String password) {
        Todo existingTodo = findById(id);
        if (existingTodo != null) { // 일정 존재 여부 확인
            if (existingTodo.getPassword().equals(password)) { // 비밀번호 확인
                todos.remove(existingTodo);
                return true; // 삭제 성공
            }
        }
        return false; // 삭제 실패
    }
}
