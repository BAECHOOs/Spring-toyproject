# MYSQL DB 구축 및 Spring with Gradle과 연동

## 개발 방법
* 개발중에는 각자 로컬 DB를 사용합니다. 스키마와 데이터는 공유합니다.
* 나중에 AWS 배포 시에 공유가 가능한 DB를 구축합니다.

## MYSQL 설치
* 설치 사이트: https://dev.mysql.com/downloads/windows/installer/8.0.html
* [참고 사이트](https://stricky.tistory.com/342) 링크
* 설치에 오랜 시간이 걸리니 먼저 하는 것을 추천합니다.

## DB 구축
* `MySQL Workbench`를 사용합니다. 로컬에 없다면 설치해주세요.
* [참고 사이트](https://withcoding.com/36)와 동일한 과정으로 진행합니다.
* DB의 1번째 버전입니다.

![DBversion1](https://user-images.githubusercontent.com/38900338/106924329-5078b800-6752-11eb-854a-09278c7facd0.JPG)
### 1. Root 권한으로 접속(사이트 참고)
### 2. 스키마 생성
* 스키마와 DB 사용자를 생성하는 부분에서 명칭 통일을 위해 아래의 캡처 사진과 동일하게 진행해주세요.
* 스키마 이름: `baechoo`

![1](https://user-images.githubusercontent.com/38900338/106919716-bc0c5680-674d-11eb-94b7-e42326c3ba1e.JPG)
### 3. DB 사용자 추가
* 사용자 이름: `admin`
* 비밀 번호: `root`

![2](https://user-images.githubusercontent.com/38900338/106919779-caf30900-674d-11eb-8c95-e49e821bd8a8.JPG)
![3](https://user-images.githubusercontent.com/38900338/106919835-d6decb00-674d-11eb-9530-2bc393febf28.JPG)
![4](https://user-images.githubusercontent.com/38900338/106919867-df370600-674d-11eb-8609-a369494a3443.JPG)

### 4. 홈으로 이동해 새로운 권한 생성(사이트 참고)
### 5. DB에 데이터 추가
* sql문은 `\spring-team\sql` 폴더에 존재합니다.
* ddl -> dml 순서로 실행해야 합니다.
* ddl에 스키마에 접속하는 부분이 포함되어 있어 실행만 시키면 됩니다.

![5](https://user-images.githubusercontent.com/38900338/106926179-2cb67180-6754-11eb-891e-9da6133105b1.JPG)

## MySQL 8.0 Command Line Client에서 DB를 확인하는 방법
* 꼭 실행하지 않아도 됩니다.
```sql
show databases; -- DB의 목록에 baechoo가 있어야 합니다.
use baechoo; -- baechoo 스키마에 접속합니다.
show tables; -- 스키마에 존재하는 테이블의 목록를 확인합니다. (User 등 4가지가 있어야 합니다.)
```

## Spring 설정
* [참고 사이트](https://memostack.tistory.com/163) 링크 
### 1. `build.gradle`에서 의존성 추가
* 제가 진행하여 깃에 추가했으므로 풀을 받아서 사용하시면 됩니다.
```
dependencies { 
    compile 'org.springframework.boot:spring-boot-starter-data-jpa' 
    compile 'mysql:mysql-connector-java' 
}
```
### 2. `application.properties` 추가
* 제가 진행하여 깃에 추가했으므로 풀을 받아서 사용하시면 됩니다.
#### (1) 사용한 코드
```
spring.datasource.url=jdbc:mysql://localhost:3306/baechoo?autoReconnect=true&useUnicode=true&characterEncoding=utf8&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC
spring.datasource.dbcp.test-on-borrow=true
spring.datasource.dbcp.validation-query=select 1
spring.datasource.username=admin
spring.datasource.password=root
spring.jpa.show-sql=true
```
* `spring.datasource.url`에서 `autoReconnect=true`는 필수
  * 추가하지 않으면 아래와 같은 에러 발생
  ```
  com.mysql.cj.exceptions.CJCommunicationsException: Communications link failure
  ```
  * 기본적으로 8시간 안에 request가 오지 않으면 강제적으로 Connection을 닫아버리기 때문에 발생하  에러
#### (2) 코드의 의미
```
spring.datasource.url=jdbc:mysql://localhost:3306/schema_name?조건들
spring.datasource.dbcp.test-on-borrow=true
spring.datasource.dbcp.validation-query=select 1
spring.datasource.username=user_name 
spring.datasource.password=password 
spring.jpa.show-sql=true
```
### 3. 실행
* 실행하여 에러가 발생하지 않으면 성공입니다.

