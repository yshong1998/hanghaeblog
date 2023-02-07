# 항해 99 Spring 입문 주차 개인 과제

## 요구 사항
1. 주어진 기능 목록을 완성할 것
2. api 명세서를 작성할 것
3. 주어진 질문 5가지에 대한 답변을 고민해 볼 것

### 기능 목록

1. 게시글 작성
2. 전체 게시글 조회
3. 특정 게시글 조회
4. 게시글 수정
5. 게시글 삭제

### api 명세서

| 기능 설명        | Method | URL            | Request                                                                                                           | Response                                                                                          |
|--------------|--------|----------------|-------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------|
| 전체 게시글 조회    | GET    | /api/posts     |                                                                                                                   | [{"title": "title","writer": "writer","contents": " contents","uploadTime": "uploadTime"}]        |
| 특정 게시글 조회    | GET    | /api/posts     |                                                                                                                   | {"title": "title 1", "writer": "writer 1", "contents": " contents 1", "uploadTime": "uploadTime"} |
| 게시글 작성       | POST   | api/posts/{id} | {"title" : "title", "contents" : "contents", "writer": "writer", "password": "password"}                          | "게시물이 업로드 되었습니다."                                                                                 |
| 게시글 수정       | PUT    | api/posts/{id} | {"title": "modified title", "writer": "modified writer", "contents": "modified contents", "password": "password"} | "게시물이 수정되었습니다."                                                                                   |
| 게시글 삭제       | DELETE | api/posts/{id} | {"password": "password"}                                                                                          | "게시물이 삭제되었습니다."                                                                                   |

### 질문과 답변
1. 수정, 삭제 API의 request를 어떤 방식으로 사용하셨나요? (param, query, body)
 - 답변 : query로 request를 사용하는 것은 url에 입력해야 하기 때문에 배포 시 유저 친화적이지 않은 방식이라고 생각해서 body에 담는 방식을 사용했습니다.
2. 어떤 상황에 어떤 방식의 request를 써야하나요?
 - 답변 :
    - param (Path Variable) : resource를 식별해야 하는 경우 (value 처리보다 path 처리가 중요할 때), 주소 바깥을 읽는다.
    - query (Query Parameter) : 정렬이나 필터링을 해야 하는 경우 (path 처리보다 value 처리가 중요할 때), 주소 바깥의 ? 이후를 &과 함께 읽는다.
    - body (주로 Json): 인수에 플랫 키 - 값 구조가 없는 경우 (이해가 잘 안 됨) or 이진 데이터 등 사람이 못 읽는 경우 or 인수가 매우 많을 경우
3. RESTful한 API를 설계했나요? 어떤 부분이 그런가요? 어떤 부분이 그렇지 않나요?
 - 답변 : 참고한 레퍼런스 https://meetup.nhncloud.com/posts/92
     - 유니폼 인터페이스 / 무상태성 / 캐시 가능 / 자체 표현 구조 / Client-Server구조 / 계층형 구조
     - 이 중 무상태성, Client-Server구조는 지켜졌다고 생각하지만 나머지 특성이 잘 드러난 API는 아니었던 것 같습니다.
4. 적절한 관심사 분리를 적용하였나요? (Controller, Repository, Service)
 - 답변 : 3번에서 나머지 특성이 잘 드러나지 않은 이유인 것 같습니다. 사실상 활용되는 Entity가 Post객체 하나 뿐이라서 이 Post객체에 대한 분리는 잘 된 듯 하지만 처음부터 객체가 Post 하나면 안 된다고 생각하여 분리가 잘 이루어졌다고 보기 힘들 것 같습니다.
5. API 명세서 작성 가이드라인을 검색하여 직접 작성한 API 명세서와 비교해보세요!
 - 답변 : 다른 부분은 괜찮았지만 Response를 아래의 형태로 반환하게 하는 리팩토링이 필요합니다. 지금은 그냥 String메세지를 날리고 있습니다.

    {  
    "code": 200,  
    "message": "success",   
    "result": "sucees create user"  
    }
