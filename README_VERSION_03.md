# 항해 99 Spring 숙련 주차 개인 과제

## Lv.3 요구 사항

1. 추가된 요구 사항을 구현할 것
2. 변경된 요구 사항을 수정할 것
3. Lv.2의 ERD를 Lv.3의 요구 사항에 맞게 수정할 것
4. Lv.2의 api명세서를 Lv.3의 요구 사항에 맞게 수정할 것
5. 주어진 질문 7개에 대해 답변해 볼 것

### 추가된 요구 사항

1. 회원 가입
    - [x] 비밀 번호에 특수 문자를 포함하도록 변경
    - [x] 회원 권한을 일반 회원과 관리자로 분리 (request에서 admin 추가)
2. 댓글 기능
    - [x] 댓글 작성 기능 추가
    - [x] 댓글 수정 기능 추가
    - [x] 댓글 삭제 기능 추가
3. 예외 처리
    - [ ] 토큰이 필요한 API 요청에서 토큰을 전달하지 않았거나 정상 토큰이 아닐 때는 "토큰이 유효하지 않습니다." 라는 에러메시지와 statusCode: 400을 Client에 반환
    - [ ] 토큰이 있고, 유효한 토큰이지만 해당 사용자가 작성한 게시글/댓글이 아닌 경우에는 “작성자만 삭제/수정할 수 있습니다.”라는 에러메시지와 statusCode: 400을 Client에 반환
    - [ ] DB에 이미 존재하는 username으로 회원가입을 요청한 경우 "중복된 username 입니다." 라는 에러메시지와 statusCode: 400을 Client에 반환
    - [ ] 로그인 시, 전달된 username과 password 중 맞지 않는 정보가 있다면 "회원을 찾을 수 없습니다."라는 에러메시지와 statusCode: 400을 Client에 반환

### 변경된 요구 사항

1. 전체 게시글 조회
   - [x] 각각의 게시글에 등록된 댓글 함께 반환
2. 선택한 게시글 조회
   - [x] 선택된 게시글에 등록된 댓글 함께 반환
3. 댓글 내림차순 정렬
   - [x] 게시글의 댓글을 내림차순으로 변경

### ERD 설계도

<img width="80%" src="https://user-images.githubusercontent.com/106438992/218950199-17dec67c-f8b0-4944-984c-aa60710988db.png"/>

### api 명세서

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

