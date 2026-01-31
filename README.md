# Spring Boot 자유게시판 (AJAX + PostgreSQL(Docker))

스프링부트 + JPA로 **게시판 1페이지**를 만들고, 화면은 **새로고침 없이(AJAX/fetch)** 목록/상세/검색/페이징/글쓰기/삭제까지 동작하도록 구현했습니다.
DB는 **PostgreSQL을 Docker 컨테이너**로 띄워서 로컬에서 바로 실행할 수 있게 구성했습니다.

---

## 데모 기능

- 게시글 **목록 조회** (AJAX)
- 게시글 **상세 조회** (AJAX)
- 게시글 **검색** (제목/내용 포함 검색)
- **페이징** (기본 5개씩)
- 게시글 **작성** (AJAX POST)
- 게시글 **삭제** (AJAX DELETE)
- **단일 페이지(SPA 느낌)**: 화면 이동(새로고침) 없이 섹션/영역만 갱신

---

## 기술 스택

- Backend: **Spring Boot**, **Spring Web**, **Spring Data JPA**
- DB: **PostgreSQL**
- Infra: **Docker / docker-compose**
- View: **Thymeleaf**
- Front: **Vanilla JS (fetch/AJAX)**

---

## 프로젝트 구조(요약)

- `BoardPageController`
  - `/board` 페이지를 렌더링(HTML 제공)
- `PostApiController`
  - `/api/posts` REST API 제공(JSON)
- `PostRepository`
  - JPA 기반 DB 조회/검색/페이징
- `templates/board.html`
  - fetch로 API 호출 → 목록/상세/검색/페이징/작성/삭제 UI 갱신

---

## 실행 방법 (로컬)

### 1) 준비물
- Java 17+
- Docker Desktop (또는 Docker)
- (선택) IntelliJ IDEA

### 2) DB 실행 (Docker)
프로젝트 루트에서:

```bash

docker compose up -d

\# 또는 구버전이면

docker-compose up -d




# Spring Boot 1페이지 게시판 (AJAX + PostgreSQL Docker)

단일 페이지에서 AJAX로 목록/상세/검색/페이징/작성/삭제가 동작하는 게시판입니다.  
DB는 Docker로 PostgreSQL을 실행해서 로컬에서 재현 가능하게 구성했습니다.

## How to Run

### 1) DB 실행 (Docker)
docker compose up -d

### 2) Spring Boot 실행
IntelliJ에서 DemoApplication 실행

### 3) 접속
http://localhost:8080/board

<img width="1260" height="672" alt="image" src="https://github.com/user-attachments/assets/0fc74e58-96ff-4871-abd8-e5d114865eea" />
<img width="1240" height="701" alt="image" src="https://github.com/user-attachments/assets/f722ea19-641b-42ca-930d-92024e39f7b4" />
<img width="1096" height="910" alt="image" src="https://github.com/user-attachments/assets/fc3f4cef-b2f3-496a-b0d6-7a7839297e0f" />

## API Endpoints
| Method | URL | Description |
|---|---|---|
| GET | /api/posts?page=&size=&query= | 목록(검색/페이징) |
| GET | /api/posts/{id} | 상세 조회 |
| POST | /api/posts | 작성 |
| DELETE | /api/posts/{id} | 삭제 |

## Key Points
- 새로고침 없이(fetch)로 화면을 갱신해 SPA 느낌으로 동작합니다.
- 최신 글이 위로 오도록 정렬(DESC)을 적용했습니다.
- DB id는 삭제 후 재사용되지 않으므로 화면용 번호(순번)는 별도로 계산해 표시했습니다.
