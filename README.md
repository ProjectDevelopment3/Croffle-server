<img width="302" alt="스크린샷 2024-02-01 오후 3 30 20" src="https://github.com/ProjectDevelopment3/Croffle-server/assets/46748334/0c965b4a-a362-4358-9f42-41db54e42713">

성신여자대학교 프로젝트 개발 및 창업 수업 팀 프로젝트입니다.

# 프로젝트 소개

크로플을 판매하는 카페를 모아볼 수 있는 서비스입니다.
게시판을 통해 자신만의 크로플 레시피를 공유할 수 있고, 카페별로 리뷰를 작성할 수 있습니다.

# ERD

<img width="880" alt="스크린샷 2024-02-01 오후 3 29 19" src="https://github.com/ProjectDevelopment3/Croffle-server/assets/46748334/2f7528e5-8b23-4785-ac54-2912509df294">

# 발표 자료

- [최종 발표 자료](https://www.miricanvas.com/ko/v/117ektv)

# 사용 기술

### Server
Spring Boot
Spring Data JPA
MySQL

### Infra
GCP
Docker
K8s
Jenkins

### 실행 전 세팅
#### application-oauth.properties 파일 생성
```
spring.security.oauth2.client.registration.naver.client-id=naver client id 입력
spring.security.oauth2.client.registration.naver.client-secret=naver client secret 입력
```
