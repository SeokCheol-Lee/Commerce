# 이커머스 프로젝트

## 개요
간단한 커머스 프로젝트

기술스택 : Spring 3.2.1, Java 17, Jpa, Mysql, Redis, Docker, AWS

목표 : 판매자와 구매자 사이를 중계해 주는 커머스 서버를 구축한다

## 회원 서버
### 공통
- [x] 회원가입
- [x] 인증
- [x] 로그인 토큰 발행
- [x] 로그인 토큰을 통한 제어 확인
- [x] 예치금 관리

## 주문 서버

### 판매자
- [x] 상품 등록, 수정
- [x] 상품 삭제

### 구매자
- [x] 장바구니를 위한 Redis 연동
- [x] 상품 검색 & 상세 페이지
- [x] 장바구니에 물건 추가
- [x] 장바구니 확인
- [x] 장바구니 변경
- [x] 주문하기

## Trouble Shooting
[go to the trouble shooting section](doc/TroubleShooting.md)

## 시스템 구성
![SCD](doc/img/SCD.png)

## ERD
![ERD](doc/img/ERD.png)


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