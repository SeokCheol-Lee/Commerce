# 이커머스 프로젝트

## 개요
간단한 커머스 프로젝트

기술스택 : Spring 3.2.1, Java 17, Jpa, Mysql, Redis, Docker, AWS

목표 : 셀러와 구매자 사이를 중계해 주는 커머스 서버를 구축한다

## 회원
### 공통
- [x] 이메일을 통해서 인증번호를 통한 회원가입

### 고객
- [x] 회원 가입
- [x] 인증
- [x] 로그인 토큰 발행
- [x] 로그인 토큰을 통한 제어 확인(JWT, Filter를 사용해서 간략하게)
- [ ] 예치금 관리

### 셀러
- [ ] 상품 CRUD ( Create, Read, Update, Delete)
- [ ] 판매 내역 조회
- [ ] 정산

## 주문
- [ ] 장바구니
- [ ] 고객의 주문

## Trouble Shooting
[go to the trouble shooting section](doc/TroubleShooting.md)


### Tech Stack
<div align=center> 
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> 
  <img src="https://img.shields.io/badge/spring boot-6DB33F?style=for-the-badge&logo=Spring Boot&logoColor=white">
  <img src="https://img.shields.io/badge/mysql-003545?style=for-the-badge&logo=mysql&logoColor=white"> 
  <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
  <img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=Docker&logoColor=white"/>
  <img src="https://img.shields.io/badge/REDIS-DC382D?style=for-the-badge&logo=Redis&logoColor=white"/>
  <img src="https://img.shields.io/badge/aws-232F3E?style=for-the-badge&logo=Amazon AWS&logoColor=white"/>
</div>