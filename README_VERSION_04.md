# 항해 99 Spring 숙련 주차 개인 과제

## Lv.4 요구 사항

1. 변경된 요구 사항을 수정할 것
2. Lv.3의 ERD를 Lv.4의 요구 사항에 맞게 수정할 것
3. Lv.3의 api명세서를 Lv.4의 요구 사항에 맞게 수정할 것 
4. 주어진 질문 4개에 대해 답변해 볼 것

### 변경된 요구 사항

1. 회원 가입
    - [x] 회원 권한 부여하기 (Lv.03에서 구현)
2. 게시글 작성 
    - [x] 토큰을 직접 검증하지 않고 Spring Security를 통해 검사 및 인증할 것
3. 선택한 게시글 수정
    - [x] 토큰을 직접 검증하지 않고 Spring Security를 통해 검사 및 인증할 것
4. 선택한 게시글 삭제
    - [x] 토큰을 직접 검증하지 않고 Spring Security를 통해 검사 및 인증할 것

### ERD 설계도

<img width="80%" src="https://user-images.githubusercontent.com/106438992/218950199-17dec67c-f8b0-4944-984c-aa60710988db.png"/>  

Lv.03과 객체가 추가되거나 관계가 변한 부분이 없기 때문에 ERD는 그대로 유지



### api 명세서

api 역시 Lv.03과의 차이는 Sprint Security의 인증 방식 외에는 없기 때문에 그대로 유지

| 기능 설명     | Method | URL               | Request                                                                                          | Response                                                                                            |
|-----------|--------|-------------------|--------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------|
| 회원 가입     | POST   | /api/user/signup  | {"username": "username", "password": "password", "admin": "boolean", "adminToken": "adminToken"} | {"statusCode": 200,"responseMessage": "signup success"}                                             |
| 로그인       | POST   | /api/user/login   | {"username": "username", "password": "password"}                                                 | {"statusCode": 200,"responseMessage": "login success"}                                              |
| 전체 게시글 조회 | GET    | /api/posts        |                                                                                                  | [{"title": "title","username": "username","contents": " contents","uploadTime": "uploadTime"}]      |
| 특정 게시글 조회 | GET    | /api/posts/{id}   |                                                                                                  | {"title": "title 1", "username": "username", "contents": " contents 1", "uploadTime": "uploadTime"} |
| 게시글 작성    | POST   | api/posts         | {"title" : "title", "contents" : "contents"}                                                     | {"title": "title 1", "username": "username", "contents": " contents 1", "uploadTime": "uploadTime"} |
| 게시글 수정    | PUT    | api/posts/{id}    | {"title": "modified title", "contents": "modified contents"}                                     | {"title": "title 1", "username": "username", "contents": " contents 1", "uploadTime": "uploadTime"} |
| 게시글 삭제    | DELETE | api/posts/{id}    |                                                                                                  | {"statusCode": 200,"responseMessage": "delete success"}                                             |
| 댓글 작성     | POST   | api/comments      | {"contents": "contents"}                                                                         | {"contents": "contents"}                                                                            |
| 댓글 수정     | PUT    | api/comments/{id} | {"contents": "contents"}                                                                         | {"contents": "contents"}                                                                            |
| 댓글 삭제     | DELETE | api/comments/{id} |                                                                                                  | {"statuscode": "statuscode", "responseMessage": "delete success"}                                   |

