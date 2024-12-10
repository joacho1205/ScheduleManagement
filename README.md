# 일정 관리 앱 만들기
Postman을 사용해서 API를 실행하고 테스트 진행했습니다.

### 공통 조건
- Entity를 그대로 반환하지 말고, DTO에 담아서 반환해야 합니다.
- 일정 작성, 수정, 조회 시 반환 받은 일정 정보에 `비밀번호`는 제외해야 합니다.
- 일정 수정, 삭제 시 선택한 일정의 `비밀번호`와 요청할 때 함께 보낸 `비밀번호`가 일치할 경우에만 가능합니다.
    - 비밀번호가 일치하지 않을 경우 적절한 오류 코드 및 메세지를 반환해야 합니다.
- `3 Layer Architecture` 에 따라 각 Layer의 목적에 맞게 개발해야 합니다.
- CRUD 필수 기능은 모두 데이터베이스 연결 및 `JDBC` 를 사용해서 개발해야 합니다. -> 하지만 제출 시점에서 데이터베이스 연결에 실패하였습니다.(24.12.10)

### API 명세서

|기능|메서드|URL|요청 데이터(JSON)|응답 데이터(JSON)|
|------|---|---|---|---|
|일정 생성|POST|/todo|{"name": "작성자명", "password": "비밀번호", "todo": "할일 내용"}|{"id": 1, "name": "작성자명", "todo": "할일 내용", "createdAt": "2024-12-10T14:00:00" }|
|전체 일정 조회|GET|/todo|없음|[{"id": 1, "name": "작성자명", "todo": "할일 내용", "updatedAt": "2024-12-10T14:00:00", ...}]|
|선택 일정 조회|GET|/todo/{id}|없음|{"id": 1, "name": "작성자명", "todo": "할일 내용", "updatedAt": "2024-12-10T14:00:00" }|
|선택 일정 수정|PUT|/todo/{id}|{"name": "수정된 작성자명", "password": "비밀번호", "todo": "수정된 할일 내용"}|"일정이 수정되었습니다."|
|선택 일정 삭제|DELETE|/todo/{id}|{"password": "비밀번호"}|"일정이 삭제되었습니다."|

### ERD

|필드 이름|데이터 타입|설명|제약 조건|
|------|---|---|---|
|id|Long|일정의 고유 식별자|Primary Key|
|name|String|작성자명|Not Null|
|password|String|비밀번호|Not Null|
|todo|String|할일 내용|Not Null|
|createAt|LocalDateTime|작성일|Not Null, 초기값 현재 시간|
|updateAt|LocalDateTime|수정일|Not Null, 초기값 현재 시간|

### SQL

```
CREATE TABLE Todo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    todo VARCHAR(100) NOT NULL,
    createdAt TIMESTAMP NOT NULL,
    updatedAt TIMESTAMP NOT NULL
);
```

### 필수 기능

#### Lv 1. 일정 생성 및 조회
- [ ]  **일정 생성(일정 작성하기)**
    - [ ]  일정 생성 시, 포함되어야할 데이터
        - [ ]  `할일`, `작성자명`, `비밀번호`, `작성/수정일`을 저장
        - [ ]  `작성/수정일`은 날짜와 시간을 모두 포함한 형태
    - [ ]  각 일정의 고유 식별자(ID)를 자동으로 생성하여 관리
    - [ ]  최초 입력 시, 수정일은 작성일과 동일
- [ ] **전체 일정 조회(등록된 일정 불러오기)**
- [ ] **선택 일정 조회(선택한 일정 정보 불러오기)**
    - [ ]  선택한 일정 단건의 정보를 조회할 수 있습니다.
    - [ ]  일정의 고유 식별자(ID)를 사용하여 조회합니다.

#### Lv 2. 일정 수정 및 삭제
- [ ]  **선택 일정 수정**
    - [ ]  선택한 일정 내용 중 `할일`, `작성자명` 만 수정 가능
        - [ ]  서버에 일정 수정을 요청할 때 `비밀번호`를 함께 전달합니다.
        - [ ]  `작성일` 은 변경할 수 없으며, `수정일` 은 수정 완료 시, 수정한 시점으로 변경합니다.
- [ ]  **선택 일정 삭제**
    - [ ]  선택한 일정을 삭제할 수 있습니다.
        - [ ]  서버에 일정 수정을 요청할 때 `비밀번호`를 함께 전달합니다.
         


# 디렉토리 구조
src/main/java

    ├─ com.spring.schedulemanagement
        ├─ controller
        ├─ service
        ├─ repository
        ├─ entity
        ├─ dto

# 주요 클래스
1. controller - TodoController : API 요청 처리 및 결과 반환
2. service - TodoService : 비즈니스 로직 처리
3. repository - TodoRepository :
4. entity - Todo : 데이터베이스의 SCHEDULE 테이블을 객체로 매핑
5. dto - a. TodoRequestDto: 클라이언트에서 받은 요청 데이터를 담음 b. TodoResponseDto: 클라이언트로 응답할 데이터를 담음


# 프로그램 구성도

<img width="547" alt="스크린샷 2024-12-10 오전 11 05 56" src="https://github.com/user-attachments/assets/714e61ef-5370-4903-b317-5448838a5b95">


