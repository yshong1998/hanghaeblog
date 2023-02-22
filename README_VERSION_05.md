# 항해 99 Spring 심화 주차 개인 과제

## Lv.4 요구 사항

1. 추가된 요구 사항을 구현할 것
2. 변경된 요구 사항을 수정할 것
3. Lv.4의 ERD를 Lv.5의 요구 사항에 맞게 수정할 것
4. Lv.4의 api명세서를 Lv.5의 요구 사항에 맞게 수정할 것
5. 주어진 질문 5개에 대해 답변해 볼 것

### 추가된 요구 사항

1. 게시글 좋아요 기능 추가
    - [x] 이미 좋아요를 누른 사용자라면 다시 요청을 보냈을 때 좋아요가 취소되도록 구현
2. 댓글 좋아요 기능 추가
    - [x] 이미 좋아요를 누른 사용자라면 다시 요청을 보냈을 때 좋아요가 취소되도록 구현
3. AOP를 이용해 예외 처리 구현
   - [ ] 토큰이 필요한 요청에서 토큰이 없거나 정상이 아닐 경우 에러 메세지와 statusCode: 400 반환   
   - [ ] 토큰이 있고 유효한 토큰이지만 해당 사용자가 작성한 게시글이 아닐 경우 에러 메세지와 statusCode: 400 반환   
   - [ ] DB에 이미 존재하는 username으로 회원가입 요청시 에러 메시지와 statusCode: 400 반환   
   - [ ] 로그인 시 username과 password중 맞지 않는 정보가 있다면 에러 메세지와 statusCode: 400 반환   
   - [ ] 회원 가입 시 username과 password의 구성이 알맞지 않으면 에러메시지와 statusCode: 400 반환
   


### 변경된 요구 사항

1. 전체 게시글 조회
    - [x] 게시글/댓글에 좋아요 개수도 함께 반환할 것
2. 선택한 게시글 조회
    - [x] 게시글/댓글에 좋아요 개수도 함께 반환할 것
3. 선택한 게시글 수정
    - [x] 게시글/댓글에 좋아요 개수도 함께 반환할 것
4. 선택한 댓글 수정
    - [x] 댓글에 좋아요 개수도 함께 반환할 것

### ERD 설계도

<img width="80%" src="https://user-images.githubusercontent.com/106438992/220600576-3147b602-61f2-4177-989f-018836151753.png"/>

### api 명세서

| 기능 설명     | Method | URL                        | Request                                                                                          | Response                                                                                            |
|-----------|--------|----------------------------|--------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------|
| 회원 가입     | POST   | /api/user/signup           | {"username": "username", "password": "password", "admin": "boolean", "adminToken": "adminToken"} | {"statusCode": 200,"responseMessage": "signup success"}                                             |
| 로그인       | POST   | /api/user/login            | {"username": "username", "password": "password"}                                                 | {"statusCode": 200,"responseMessage": "login success"}                                              |
| 전체 게시글 조회 | GET    | /api/posts                 |                                                                                                  | [{"title": "title","username": "username","contents": " contents","uploadTime": "uploadTime"}]      |
| 특정 게시글 조회 | GET    | /api/posts/{id}            |                                                                                                  | {"title": "title 1", "username": "username", "contents": " contents 1", "uploadTime": "uploadTime"} |
| 게시글 작성    | POST   | api/posts                  | {"title" : "title", "contents" : "contents"}                                                     | {"title": "title 1", "username": "username", "contents": " contents 1", "uploadTime": "uploadTime"} |
| 게시글 수정    | PUT    | api/posts/{id}             | {"title": "modified title", "contents": "modified contents"}                                     | {"title": "title 1", "username": "username", "contents": " contents 1", "uploadTime": "uploadTime"} |
| 게시글 삭제    | DELETE | api/posts/{id}             |                                                                                                  | {"statusCode": 200,"responseMessage": "delete success"}                                             |
| 댓글 작성     | POST   | api/comments               | {"contents": "contents"}                                                                         | {"contents": "contents"}                                                                            |
| 댓글 수정     | PUT    | api/comments/{id}          | {"contents": "contents"}                                                                         | {"contents": "contents"}                                                                            |
| 댓글 삭제     | DELETE | api/comments/{id}          |                                                                                                  | {"statuscode": "statuscode", "responseMessage": "delete success"}                                   |
| 게시글 좋아요   | POST   | api/postlike/{postId}      |                                                                                                  | {"statuscode": "statuscode", "responseMessage": "post like success"}                                |
| 댓글 좋아요    | POST   | api/commenlike/{commentId} |                                                                                                  | {"statuscode": "statuscode", "responseMessage": "commment like success"}                            |

