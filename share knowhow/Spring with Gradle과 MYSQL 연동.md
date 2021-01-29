# Spring with Gradle과 MYSQL 연동

## MYSQL 설치
* 설치 사이트: https://dev.mysql.com/downloads/windows/installer/8.0.html
* https://stricky.tistory.com/342 참고
* 설치에 오랜 시간이 걸리니 미리 하는 것을 추천합니다.

## Spring과의 연동
* https://memostack.tistory.com/163 참고
### MYSQL
* MYSQL에서 유저 생성 및 권한 부여
```
CREATE USER user_name@localhost IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON table_name.* TO user_name@localhost;
```
* 스미카 및 테이블 생성
### Spring
* `build.gradle`에서 의존성 추가
```
dependencies { 
    compile 'org.springframework.boot:spring-boot-starter-data-jpa' 
    compile 'mysql:mysql-connector-java' 
}
```
* `application.properties`에 데이터소스 작성
```
spring.datasource.url=jdbc:mysql://localhost:3306/table_name?autoReconnect=true&useSSL=false&useUnicode=true&serverTimezone=Asia/Seoul 
spring.datasource.username=user_name 
spring.datasource.password=password 
spring.jpa.show-sql=true
```
  * `spring.datasource.url`에서 `autoReconnect=true`는 필수
    * 추가하지 않으면 아래와 같은 에러 발생
    ```
    com.mysql.cj.exceptions.CJCommunicationsException: Communications link failure
    ```
    * 기본적으로 8시간 안에 request가 오지 않으면 강제적으로 Connection을 닫아버리기 때문에 발생하는 에러

### 실행


## 개발을 위한 MYSQL
### 방법1: 로컬 DB 사용, 스키마와 데이터를 공유
### 방법2: 한 개의 컴퓨터를 서버처럼 사용, 외부에서 접속 