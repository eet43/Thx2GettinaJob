# 📚 취뽀의 자격

<p align="center">
  <br>
  <img src="https://github.com/eet43/Thx2GettinaJob/assets/59008469/1aa58117-14b3-4857-82cc-f3c989edfddb" height="400px" width="15.5%">
  <img src="https://github.com/eet43/Thx2GettinaJob/assets/59008469/28195698-bfc7-43ca-b28d-b53200199135" height="400px" width="15.5%">
  <img src="https://github.com/eet43/Thx2GettinaJob/assets/59008469/2ad3af11-b959-4bd3-8ffe-1ff5c53f580d" height="400px" width="16.66%">
  <img src="https://github.com/eet43/Thx2GettinaJob/assets/59008469/8a85d299-e9bb-4b81-9318-05310f6eb35d" height="400px" width="16.66%">
  <img src="https://github.com/eet43/Thx2GettinaJob/assets/59008469/0071ea67-6108-49c3-a442-2edb43094ed0" height="400px" width="16.66%">
  <img src="https://github.com/eet43/Thx2GettinaJob/assets/59008469/9f906ca2-fe37-4224-b8d1-12355c021220" height="400px" width="16.66%">
  <br>
</p>

## 🙇🏻‍♂️ 프로젝트 소개

<p align="justify">
취업 준비를 위해 자소서를 작성하면서 매번 각각 자격증 사이트에서 수험번호와 시험 일정을 확인하는 과정이 번거롭지 않으셨나요?

이런 Pain point 개선을 위해 어학시험과 공인자격증 시험 접수일정/시험 일시/재시험 기간/결과 확인 통합 플랫폼 앱 개발 프로젝트를 진행했습니다.
</p>

<br>


## 🔥 구현 기능
- <b>회원</b>
  - 이메일 인증(5분), 회원가입
  - 로그인 인증처리(토큰)
  - 프로필 조회, 수정
  - 임시 비밀번호 발급
- <b>시험</b>
  - 자격증 시험 데이터 일정 크롤링 가져오기 (토익, 토익스피킹, HSK, APFK, 한국사 등)
  - 없는 자격증 시험 직접 일정 등록 (자동으로 즐겨찾기 추가)
  - 직접 등록한 자격증 시험 정보 수정
  - 자격증 시험 검색 및 조회 (접수상태, D-day 포함)
  - 회차 별 자격증 조회
  - 실시간 인기있는 자격증 시험 조회
- <b>즐겨찾기</b>
  - 즐겨찾기 추가, 삭제
  - 즐겨찾기 된 자격증 일정 검색 및 조회 (접수상태, D-day 포함)
  - 접수 마감 얼마 남지 않은 자격증 시험 조회
- <b>캘린더</b>
  - 즐겨찾기 리스트 조회 (시험 별 항목있는지 조회 후 boolean 값으로 전달)
  - 필터링 기능 (날짜, 시험항목, 검색항목)
  - 필터링 한 자격증 시험 일정 리스트 조회
- <b>내점수</b>
  - 내점수 등록 및 수정 (유효기간 비교해서 유효성 자동 판단)
  - 내 자격증 점수 리스트 조회
  - 하루마다 자격증 유효성 검사 (배치 및 이벤트처리)

<br>

## 🔓 ERD

![KH-ONETOP](https://github.com/eet43/Thx2GettinaJob/assets/59008469/4373e3f0-4e6d-48d3-85fd-46e6559052ec)

<br>

## 🌴 기술 스택
### 🍊 SERVER
- <b>Java 17</b>
- <b>Spring boot 3.0.4</b>  
  - 의존성 관리
  - Auto Configuration
  - record 타입 추가
- <b>Spring Security</b>  
  - JWT 기반 토큰을 활용한 인증
- <b>Spring Data JPA</b>  
  - 객체 중심의 ORM 쿼리 활용
- <b>QueryDSL</b>
  - 컴파일 단게에서 쿼리 에러 검출
  - 필터링에 필요한 동적 쿼리를 효율적으로 작성
### 🍎 DB
- <b>MySQL 8.0.30</b>  
  - 테이블의 성격을 고려한 객체 중심의 엔티티 설계
  - 시스템 복잡도를 줄이기 위해 라이프사이클이 같은 엔티티만 연관관계로 설정

## 개선 과정


<br>
