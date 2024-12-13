# Servlet-Project-Notice Board
서블렛 + 게시판 프로그램
## 프로젝트 소개
서블렛을 이용한 첫 프로젝트입니다.
## 개발 기간
2024.02.20 ~ 2024.03.25

## 멤버구성
팀장 : 강인서
팀원 1: 박석훈
팀원 2: 조민석
팀원 3: 백승건

## 팀원 역할
<https://docs.google.com/spreadsheets/d/1J9ZjCIkxQlzxwjg-giMRJ1iJRmeHPDW1FNWiLh7k-Zw/edit?gid=0#gid=0>

## 개발 환경
- `Java 8`
- `JDK-21`
- `Eclipse`
- Database : `Oracle`
- ORM : `Mybatis`

# 주요 기능

### 메인 화면
- 게시판
- 전체 게시글 조회
- 로그인 or 로그아웃
- 마이페이지

### 로그인 
- 아이디 찾기
- 비밀번호 찾기
- 로그인
- 로그아웃

### 회원가입
- 아이디 중복 체크
- 회원가입

### 게시판,게시글,댓글,카테고리
- 게시판 CRUD
- 게시글 CRUD
- 댓글 CRUD
- 카테고리 CRUD

### 마이 페이지
- 회원 탈퇴
- 정보 변경
- 키워드 등록
- 내가 쓴 글
- 내가 쓴 댓글

### 관리자
- 모든 게시판,게시글,댓글,카테고리 삭제 가능

## 맡은 기능 설명
로그인 - <a href="https://github.com/park-seok-hoon/Second_Project/wiki/%EC%A3%BC%EC%9A%94-%EA%B8%B0%EB%8A%A5-%EC%86%8C%EA%B0%9C(%EB%A1%9C%EA%B7%B8%EC%9D%B8)" > 상세보기 -로그인</a>
- ID 중복 체크

회원가입 - <a href="https://github.com/park-seok-hoon/Second_Project/wiki/%EC%A3%BC%EC%9A%94-%EA%B8%B0%EB%8A%A5-%EC%86%8C%EA%B0%9C(%ED%9A%8C%EC%9B%90%EA%B0%80%EC%9E%85)" > 상세보기 -회원가입</a>
- ID 중복 체크
- 회원가입 유효성 검사

댓글 - <a href="https://github.com/park-seok-hoon/Second_Project/wiki/%EC%A3%BC%EC%9A%94-%EA%B8%B0%EB%8A%A5-%EC%86%8C%EA%B0%9C(%EB%8C%93%EA%B8%80)" > 상세보기 - 댓글</a>
- 댓글 작성,읽기,수정,삭제(CRUD)

## 어려웠던 부분
<details>
<summary>post와 get을 쓰는 경우</summary>
  post를 쓰는 경우는 URL에 value 값이 보이는 것을 방지하기 위해서 사용한다는 것을 이론적으로 알기는 했지만 get을 써주지 않으면 web에 화면이 뜨지 않는다는 것을 하면서 알게 되었습니다.
  get을 쓰는 경우와 post를 쓰는 경우가 처음에 굉장히 헤깔렸습니다.
  그러나 post와  get을 쓰는 경우를 계속 접하다 보니 알게 되었습니다.
</details>

<details>
<summary>Session을 언제 사용하는 것인지</summary>
  회원가입을 하면 DB에 회원의 정보가 저장이 되는데 로그인을 할 때 회원가입 되어 있는 회원 정보를 가져오고 ID와 PW가 맞으면 session에 저장을 하면 된다라고 일단 따라쳤었지만 
  이유에 대해서는 궁굼했습니다.
  session을 쓰면 web을 종료하지 않는 이상 보통 30분 이상 정보가 저장이 되어서 서버를 재가동하더라도 로그인이 된채로 작업한 내용에 대해서 확인을 할 수 있다는 장점이 있다는 것에 대해서 알게 되었습니다.
</details>

<details>
<summary>Mapper 오류</summary>

  ```

<mapper namespace="team5_servlet.kr.kh.team5.dao.AdminDAO"> 

  ```
  지금 보는 예시와 같이 경로 설정을 관리자 DAO에 대한 관리자 MAPPER에 연결을 해야 하는데 다른 Mapper에 연결을 하여 오류가 뜨는 문제점이 있었지만 경로 설정을 잘못했다는 문제점을 알게되었습니다.


```
DAO
	AuthorityVO selectRoleByAname(@Param("a_name")String name);
```

```
 Mapper
  <select id="selectRoleByAname" resultType="team5_servlet.kr.kh.team5.model.vo.AuthorityVO">
		SELECT a_name, LPAD(a_list, 6, 0) AS a_list FROM authority WHERE a_name = #{a_name}
	</select>
```

DAO에서 설정한 추상메소드 이름과 mapper에서 id 명을 동일하게 해야지만 받을수 있다는 것을 오류가 나고 나서 알게 되었고,
객체가 가지고 있는 변수값을 받을 때는 항상 resultType에 경로명을 명시해줘야만 한다는 것을 알게 되었습니다.
또한 select문을 쓸 때는 resultType을 쓰지만 select문이 아닐 때는 resultType을 쓰지 않는 것 또한 알게 되었습니다.

</details>



