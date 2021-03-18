## Usage

### Initialize project

```shell
$ git clone https://github.com/BAECHOOs/Spring-toyproject.git
```

<br>

### DB schema create

```sql
-- MySQL
CREATE DATABASE baechoo;
```

<br>

### Configure create

```shell
$ cd spring-team/src/main/resources
$ vi configure.properties
```

```properties
# configure.properties
spring.datasource.url = jdbc:mysql://localhost:3306/baechoo?autoReconnect=true&useUnicode=true&characterEncoding=utf8&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Asia/Seoul
spring.datasource.username = USERNAME
spring.datasource.password = PASSWORD
cloud.aws.credentials.accessKey = ACCESS_KEY
cloud.aws.credentials.secretKey = SECRET_KEY
cloud.aws.s3.bucket = BUCKET_NAME
cloud.aws.region.static = ap-northeast-2
```

<br>

### Deploy test

```shell
$ cd spring-team
$ ./gradle test
```

<br>

### Deployment

```shell
$ cd spring-team
$ ./gradle build
$ build/libs
$ java -jar spring-team-0.0.1-SNAPSHOT.jar
```

