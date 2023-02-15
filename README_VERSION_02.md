# 항해 99 Spring 숙련 주차 개인 과제

## Lv.2 요구 사항

1. 추가 기능을 완성할 것
2. 기존 기능을 수정할 것
3. api 명세서를 작성할 것
4. ERD를 설계해 볼 것
5. 유스케이스 다이어그램을 그려볼 것
6. 주어진 질문 6가지에 대한 답변을 고민해 볼 것

### 추가 기능 목록

1. 회원 가입
2. 로그인

### 수정 기능 목록

1. 게시글 작성 - 토큰 검사, 제목, 작성자명, 작성 내용 저장
2. 전체 게시글 조회 - 작성자명 추가 조회
3. 특정 게시글 조회 - 작성자명 추가 조회
4. 게시글 수정 - 토큰 검사, 사용자 검사, 내용 수정
5. 게시글 삭제 - 토큰 검사, 사용자 검사

### api 명세서

| 기능 설명     | Method | URL              | Request                                                      | Response                                                                                            |
|-----------|--------|------------------|--------------------------------------------------------------|-----------------------------------------------------------------------------------------------------|
| 회원 가입     | Post   | /api/user/signup | {"username": "username", "password": "password"}             | {"statusCode": 200,"responseMessage": "signup success"}                                             |
| 로그인       | Post   | /api/user/login  | {"username": "username", "password": "password"}             | {"statusCode": 200,"responseMessage": "login success"}                                              |
| 전체 게시글 조회 | GET    | /api/posts       |                                                              | [{"title": "title","username": "username","contents": " contents","uploadTime": "uploadTime"}]      |
| 특정 게시글 조회 | GET    | /api/posts       |                                                              | {"title": "title 1", "username": "username", "contents": " contents 1", "uploadTime": "uploadTime"} |
| 게시글 작성    | POST   | api/posts/{id}   | {"title" : "title", "contents" : "contents"}                 | {"title": "title 1", "username": "username", "contents": " contents 1", "uploadTime": "uploadTime"} |
| 게시글 수정    | PUT    | api/posts/{id}   | {"title": "modified title", "contents": "modified contents"} | {"title": "title 1", "username": "username", "contents": " contents 1", "uploadTime": "uploadTime"} |
| 게시글 삭제    | DELETE | api/posts/{id}   |                                                              | {"statusCode": 200,"responseMessage": "delete success"}                                             |

### ERD 설계도

<img width="80%" src="https://user-images.githubusercontent.com/106438992/218947231-878306fb-3071-4094-8f3f-1c22ae6bd2cc.png"/>

### 유스케이스 다이어그램

<img width="80%" src="https://user-images.githubusercontent.com/106438992/218940038-5d1cc860-609a-42c5-b5c5-8ab4b97105e6.jpg"/>

### 질문과 답변

1. 처음 설계한 API 명세서에 변경사항이 있었나요?   
   변경 되었다면 어떤 점 때문 일까요? 첫 설계의 중요성에 대해 작성해 주세요!
    - 따로 변경 사항은 없었습니다. 노션에서 요청과 응답으로 어떤 객체를 주고 받는지 잘 적혀 있어서 그대로 했습니다.
    - 명세서상으로는 달라진 게 없지만 반환 타입을 ResponseEntity로 변경하기는 했습니다.
2. ERD를 먼저 설계한 후 Entity를 개발했을 때 어떤 점이 도움이 되셨나요?
    - ERD를 설계하고 나니까 둘의 관계가 시각화 되어서 머릿 속에 구조가 더 잘 그려졌던 것 같습니다. 그래서인지 의외로 이번 과제 중에는 에러가 별로 없었습니다.
3. JWT를 사용하여 인증/인가를 구현 했을 때의 장점은 무엇일까요?
    - 적어도 secret key가 노출되지 않는 이상, 서버에서는 인증과 인가를 잘못 내줄 일이 없습니다. 또한 토큰 유지 조건 혹은 시간까지 직접 설정이 가능하다는 점도 큰 장점이었던 것 같습니다.
4. 반대로 JWT를 사용한 인증/인가의 한계점은 무엇일까요?
    - seceret key가 말 그대로 아킬레스이기도 하다는 점입니다. seceret key가 노출당하면 정말 외부에서 서버 내 DB의 대부분의 정보에 접근할 수 있게 됩니다.
5. 만약 댓글 기능이 있는 블로그에서 댓글이 달려있는 게시글을 삭제하려고 한다면 무슨 문제가 발생할까요? Database 테이블 관점에서 해결방법이 무엇일까요?
    - 게시글은 사라졌는데 해당 게시글과 연결되어 있는 댓글들은 아직 사라지지 않은 상태이기 때문에 여전히 서버의 리소스를 잡아먹는 상태로 남게 되고, 댓글이 화면 상에 나타날 일도 없어 이를 방치하게 될 가능성이 높습니다.
    - Database 테이블 관점에서, 삭제할 때 해당 게시글 자체를 댓글들의 외부 키로 갖고 있도록 한 후, 이 키가 일치하는 댓글을 모두 같이 삭제하도록 하면 되지 않을까 생각합니다.
6. IoC / DI 에 대해 간략하게 설명해 주세요!
    - DI : 의존성 주입이란 하나의 객체가 다른 객체의 의존성을 제공하는 기술입니다. 즉 어떠한 객체가 생성되거나 안에 다른 객체를 참조하고 있는 경우 의존성이 주입된 것이라고 할 수 있습니다.의존성 주입의 방법에는 생성자 주입 / 필드 주입 / 수정자 주입이 있습니다.
    - IoC : 제어 역전의 개념은 전통적인 프로그래밍에서 보통 라이브러리나 프레임워크의 기능을 프로그래머가 호출해 와서 제어 흐름을 형성하는 것이 일반적이지만, 제어 역전은 프레임 워크나 라이브러리에서 프로그래머의 코드를 호출해 와 제어 흐름의 주도권을 가져갑니다.
