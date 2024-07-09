# FIND-ANIMAL

**백엔드 Repository 입니다.**

[프론트 Repositroy](https://github.com/find-animal/fe)<br/>
## 프로젝트 특징

* 유기동물 공공데이터를 활용한 유기동물 조회 및 입양을 권장하는 서비스 

* 프론트엔드와 백엔드를 분리하여 프로젝트 개발
    * 각 파트의 별도 Repository를 생성 후 작업
    * 프론트 : React 프레임워크 이용
    * 백엔드 : Spring Boot를 이용

* 로그인 처리는 Spring Boot Security와 Jwt Token방식으로 처리

* JPARepository와 QueryDsl 사용

* Controller test는 Postman으로 진행

* jwt와 유기동물 조회 다중 필터 기능 테스트 코드 작성 (test용 h2 db 사용)

* 공공데이터를 활용하여 데이터를 전처리 과정을 거치며 데이터베이스에 추가

* jenkins를 활용하여 ci/cd를 구축 테스트 코드가 통과시에 빌드 후 배포 

## 개요

* 명칭 : find_animal

* 개발 기간 : 2024.05.19 ~ 2024.07에 끝날 예정

* 주요 기능
    * 공공데이터의 유기동물, 보호소의 실시간 정보를 각 엔티티의 조건에 맞게 db에 삽입기능
    * 관심 보호소, 관심 유기동물 기능 
    * 로그인, 회원가입 기능
    * 유기동물 네이버 뉴스 기능

* 개발 환경 : Springboot 3.2.25 java 17, MariaDB
* 
* CI/CD : aws, jenkins

* 형상 관리 툴 : git

* 협업 툴 : Slack, Discord

## 테이블 설계
![animalDB](https://github.com/find-animal/BE_find_animal/assets/94777814/7e35ce48-79d5-441a-bfe7-240b5b3afbaa)


## API 설계
![image](https://github.com/find-animal/BE_find_animal/assets/94777814/5043965c-2a1f-4554-bcb1-1bfb20417220)
![image](https://github.com/find-animal/BE_find_animal/assets/94777814/1b7df19c-7d31-4a34-8a9e-8d696f064251)
![image](https://github.com/find-animal/BE_find_animal/assets/94777814/fafaa845-75ff-4599-95c5-ae517f772a80)


## CI/CD 설계
![image](https://github.com/find-animal/BE_find_animal/assets/94777814/6970c2c2-e5d6-4149-8ca7-860d1c509a32)
