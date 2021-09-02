

# 📢 배추마켓: Springboot와 AWS를 활용한 중고거래 사이트

<br>

<div align="center">
   <img src="https://user-images.githubusercontent.com/41130448/105579921-b8cbaf00-5dcc-11eb-8655-2f09bf64ede9.png" width="40%"></img>
</div>

<br><br>

## 🙋‍♂️ Spring을 함께 공부하고자 모인 사람들

- <a href="https://github.com/mingdyuo" target="_blank">mingdyuo 님</a> 
- <a href="https://github.com/HYEEWON " target="_blank">HYEEWON 님</a> 
- <a href="https://github.com/jiyunYun" target="_blank">jiyunYun님</a> 
- <a href="https://github.com/osj3474" target="_blank">osj3474님</a>

<br><br>

## 📆 프로젝트 기간

2021.01.25. ~ 2021.03.18

| 기간                | 설명                                                         |
| ------------------- | ------------------------------------------------------------ |
| 1.23(토)            | 전체 회의                                                    |
| 1.25(월) ~ 1.30(토) | 준비 기간                                                    |
| 1.31(일) ~ 2.03(수) | 메인화면 프론트 구성<br />security/database 팀 분담<br />DB 스키마 구성<br />메인화면 레이아웃 구성 |
| 2.04(목) ~ 2.15(월) | 페이지 역할 분담<br />Spring security로 회원가입, 로그인 구현<br />API 스펙 구성하기<br />DB 스키마 최종 완료<br />홈페이지 디자인 및 상품 리스트 |
| 2.16(화) ~ 2.25(목) | Entity 클래스 설계 및 JPA로 연관관계 설정<br />마이페이지 제작 |
| 2.26(금) ~ 3.01(월) | 판매 글쓰기: Post API 제작                                   |
| 3.02(화) ~ 3.18(목) | 판매 글쓰기: AWS S3에 이미지 저장 기능 구현                  |

<br><br>

## 📍 사용하는 툴

1. Java : 11 version
2. Spring Boot : 2.4.2
3. Build Tool : Gradle
4. DB : MySQL
5. Deploy : AWS EC2
6. 협업 : [github issue](https://github.com/BAECHOOs/Spring-toyproject/issues), [project](https://github.com/BAECHOOs/Spring-toyproject/projects/1)
7. Etc: Thymeleaf, Spring Security, JPA, AWS S3

<br><br>

## 🛠 아키텍처

![image](https://user-images.githubusercontent.com/41130448/111637968-3d6f0280-883d-11eb-82eb-aa2f4e7ac06d.png)

<br><br>

## 💾 DB 스키마 구성

![image](https://user-images.githubusercontent.com/41130448/111624650-4ce74f00-882f-11eb-9be7-f5024abbd4da.png)

<br><br>

## 🎯 구현 결과

#### 1) 회원가입/로그인

- [x] 회원가입
- [x] 로그인

![image](https://user-images.githubusercontent.com/41130448/111629753-faa92c80-8834-11eb-8ec6-566e31a2c0d7.png)

![image](https://user-images.githubusercontent.com/41130448/111629791-04329480-8835-11eb-901a-0dbf7eadca11.png)

<br><br>

#### 2) 메인 페이지(홈)

- [x] 물건 리스트 보기
- [x] 물건 업로드
- [x] 물건 상세페이지 보기



![image](https://user-images.githubusercontent.com/41130448/111629916-22989000-8835-11eb-9d23-fc7ce85de814.png)

![image](https://user-images.githubusercontent.com/41130448/111629983-347a3300-8835-11eb-8f90-bd30fd610a73.png)

![image](https://user-images.githubusercontent.com/41130448/111629855-14e30a80-8835-11eb-8dcf-70b2366ecfbe.png)

![image](https://user-images.githubusercontent.com/41130448/111630236-7dca8280-8835-11eb-9f42-4f0128231c82.png)

![image](https://user-images.githubusercontent.com/41130448/111630209-74411a80-8835-11eb-8e17-c4756cddca21.png)

<br><br>

#### 3) 마이 페이지

- [x] 좋아요 내역, 판매/구매 내역
- [x] 내 정보 보기

<br><br>

## 🥁 실행 방법
[클릭](https://github.com/BAECHOOs/Spring-toyproject/blob/main/How%20to%20build.md)

<br><br>

## 🔖 노하우 공유

[[DB] IntelliJ DB Navigator에서 DB 작업하기](https://github.com/BAECHOOs/Spring-toyproject/blob/main/share%20knowhow/%5BDB%5D%20IntelliJ%20DB%20Navigator%EC%97%90%EC%84%9C%20DB%20%EC%9E%91%EC%97%85%ED%95%98%EA%B8%B0.md)

[[DB] MYSQL DB 구축 및 Spring with Gradle과 연동](https://github.com/BAECHOOs/Spring-toyproject/blob/main/share%20knowhow/%5BDB%5D%20MYSQL%20DB%20%EA%B5%AC%EC%B6%95%20%EB%B0%8F%20Spring%20with%20Gradle%EA%B3%BC%20%EC%97%B0%EB%8F%99.md)

[[DB] MySQL 참고 사항](https://github.com/BAECHOOs/Spring-toyproject/blob/main/share%20knowhow/%5BDB%5D%20MySQL%20%EC%B0%B8%EA%B3%A0%20%EC%82%AC%ED%95%AD.md)

[[Git] Git project](https://github.com/BAECHOOs/Spring-toyproject/blob/main/share%20knowhow/%5BGit%5D%20Git%20project.md)

[[Git] Github for collaboration](https://github.com/BAECHOOs/Spring-toyproject/blob/main/share%20knowhow/%5BGit%5D%20Github%20for%20collaboration.md)

[[Spring] DAO와 DTO](https://github.com/BAECHOOs/Spring-toyproject/blob/main/share%20knowhow/%5BSpring%5D%20DAO%EC%99%80%20DTO.md)

[[Spring] Spring Data JPA 정리](https://github.com/BAECHOOs/Spring-toyproject/blob/main/share%20knowhow/%5BSpring%5D%20Spring%20Data%20JPA%20%EC%A0%95%EB%A6%AC.md)

[[Spring] Spring Security](https://github.com/BAECHOOs/Spring-toyproject/blob/main/share%20knowhow/%5BSpring%5D%20Spring%20Security.md)

[[Spring] Springboot build and deploy tools](https://github.com/BAECHOOs/Spring-toyproject/blob/main/share%20knowhow/%5BSpring%5D%20Springboot%20build%20and%20deploy%20tools.md)

[[Spring] Thymeleaf정리](https://github.com/BAECHOOs/Spring-toyproject/blob/main/share%20knowhow/%5BSpring%5D%20Thymeleaf%EC%A0%95%EB%A6%AC.md)

[[Spring] 도메인 클래스 관련 참고사항(1)](https://github.com/BAECHOOs/Spring-toyproject/blob/main/share%20knowhow/%5BSpring%5D%20%EB%8F%84%EB%A9%94%EC%9D%B8%20%ED%81%B4%EB%9E%98%EC%8A%A4%20%EA%B4%80%EB%A0%A8%20%EC%B0%B8%EA%B3%A0%EC%82%AC%ED%95%AD(1).md)

[[Spring] 도메인 클래스 관련 참고사항(2)](https://github.com/BAECHOOs/Spring-toyproject/blob/main/share%20knowhow/%5BSpring%5D%20%EB%8F%84%EB%A9%94%EC%9D%B8%20%ED%81%B4%EB%9E%98%EC%8A%A4%20%EA%B4%80%EB%A0%A8%20%EC%B0%B8%EA%B3%A0%EC%82%AC%ED%95%AD(2).md)

[[Spring] 서버 재시작하지 않고 view 변경 확인하기](https://github.com/BAECHOOs/Spring-toyproject/blob/main/share%20knowhow/%5BSpring%5D%20%EC%84%9C%EB%B2%84%20%EC%9E%AC%EC%8B%9C%EC%9E%91%ED%95%98%EC%A7%80%20%EC%95%8A%EA%B3%A0%20view%20%EB%B3%80%EA%B2%BD%20%ED%99%95%EC%9D%B8%ED%95%98%EA%B8%B0.md)

<br><br>



## ☝ 이렇게 약속합시다 

#### ✅ commit 메세지는 2가지를 지켜주세요~!

1. 첫 글자는 대문자로 해주세요.

2. 명령문으로 해주세요.

   ex) Fixed bug (X), Fix bug (O)

   [좋은 git 커밋 메시지를 작성하기 위한 8가지 약속](https://djkeh.github.io/articles/How-to-write-a-git-commit-message-kor/)



#### ✅ 데이터베이스 key나 passward 등 보안과 관련된 내용은 public하게 올리면 안돼요~!



#### ✅ 배포 서버 상에서 코드를 수정하면 안돼요 ! IDE에서 수정, push 후에 github를 통해 pull 받도록 합니다.



#### ✅ 충돌이 있는지 정상적으로 실행되는지 확인하고 PR해주세요.



<br>







