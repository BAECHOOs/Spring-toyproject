### 스프링 부트 빌드 관리 툴

1. 메이븐 (maven)
   - XML을 기반으로 하며 의존성 및 디렉토리 구조 관리의 기능을 제공함
   - 라이브러리가 추가되거나, 서로 다른 버전의 라이브러리를 참조하는 종속성을 가질 경우 관리가 어려움
   - XML의 구조화는 사람 입장에서 가독성은 좋으나, 문서의 양이 방대해짐
2. 그레이들 (gradle)
   - JVM 기반의 빌드 툴이며 메이븐과 호환됨
   - 설정이 간편, 프로젝트별로 유연하게 설정 가능
   - 최근에는 세계적으로 gradle을 많이 사용하는 추세이며, 깃과 같은 오픈 소스 라이브러리들도 대부분 gradle을 사용

#### 📌 이번 프로젝트에서는 빌드 툴로써 Gradle을 선택한다.

<br>

### build.gradle

- 그레이들로 생성된 프로젝트의 빌드를 관리하는 파일
- 그레이들 파일의 내용 구조 및 예시

```java
/* 플러그인 의존성 관리 설정 */
buildscript {
    // 전역변수 설정하는 키워드
    ext { 
        springBootVersion = '2.1.9.RELEASE'
        /* 스프링 부트의 버전 설정, 이 값은 ${springBootVersion} 형식으로 사용 */
    }
    repositories {
        mavenCentral()
        jcenter()
    }
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")        
    }
}

apply plugin: 'java'
apply plugin: 'eclipse'
apply plugin: 'org.springframework.boot' /* 스프링 부트 플러그인 적용을 의미 */
apply plugin: 'io.spring.dependency-management'


group 'com.mingdyuo.book'
version '1.0-SNAPSHOT'
sourceCompatibility = 1.8 /* 자바 8을 사용하도록 설정 */


repositories { /* 각종 의존성(라이브러리)를 어떤 원격 저장소에서 받을 지 정하는 것 */
    mavenCentral()
    jcenter()
    /* 아래와 같이 외부 저장소의 라이브러리도 사용 가능 */
    maven { url "https://repo.spring.io/snapshot" }
}

dependencies{ /* 필요한 라이브러리 명시, 라이브러리 추가할 때마다 변경함 */
    // START
    compile('org.springframework.boot:spring-boot-starter-web')
    compile('org.projectlombok:lombok')
    testCompile('org.springframework.boot:spring-boot-starter-test')
    // JPA DATA
    compile('org.springframework.boot:spring-boot-starter-data-jpa')
    compile('com.h2database:h2')
    // Mustache
    compile('org.springframework.boot:spring-boot-starter-mustache')
    // 소셜 로그인
    compile('org.springframework.boot:spring-boot-starter-oauth2-client')
    // 세션 디비 저장소
    compile('org.springframework.session:spring-session-jdbc')
    // 시큐리티 테스트
    testCompile('org.springframework.security:spring-security-test')
    // Maria DB 드라이버
    compile('org.mariadb.jdbc:mariadb-java-client')
}

```

<br>

### 스프링 부트의 배포 옵션

스프링 부트 애플리케이션을 실행, 배포하는 방법은 다음과 같다. **메이븐, 혹은 그래들을 이용해서** 애플리케이션을 **<u>빌드 및 실행</u>** 혹은 **<u>실행 가능한 파일을 생성</u>**하는 것이다.

다시 말해,

1. 애플리케이션을 빌드 및 실행하거나

   - IDE(STS, 인텔리제이 등)에서 애플리케이션을 빌드하고 실행

   - 메이븐의 `springboot:run`, 혹은 그래들 `bootRun` 태스크로 빌드 및 실행

2. 실행 가능한 파일을 생성하기

   - 메이븐이나 그래들을 사용해서 <u>실행 가능한</u> JAR 파일 생성

     (<u>명령행에서 실행</u>되거나 클라우드에 배포될 수 있음)

   - 메이븐이나 그래들을 사용해서 WAR 파일 생성

     (자바 애플리케이션 서버에 배포될 수 있음)

프로덕션 환경에서는 2번 방법을 이용하여 애플리케이션을 배포한다. 그렇다면, JAR과 WAR 중 어느 파일 형식을 선택해야 할까? 이는 배포하는 환경에 따라 선택한다.

1. 자바 애플리케이션 서버에 배포하는 경우 (Tomcat, WebSphere, WebLogic 등)

   → WAR 파일을 선택한다. (선택의 여지가 없음)

2. 클라우드에 배포하는 경우 (Cloud Foundry, AWS, Azure, Google cloud platform 등)

   → <u>실행 가능한</u> JAR 파일을 선택하는 것이 좋다. 

   > WAR 형식보다 JAR 형식이 훨씬 간단하므로 클라우드 플랫폼에서 WAR 파일 배포를 지원하더라도, JAR 파일으로 배포하는 것이 좋다.

#### 📌 이번 프로젝트에서는 AWS를 사용하므로 JAR 파일 배포를 선택한다.

<br>

### AWS EC2 생성 및 설정

1. 아마존 리눅스 AMI, SSD Volume 서버를 선택, 스토리지는 30GB혹은 그 이상으로 설정 (30GB는 프리 티어에서 사용할 수 있는 최대 용량임)

2. 태그 추가

   키는 `Name`으로 값은 `Baechoo-market` 등의 우리의 프로젝트 이름으로 설정

3. 보안 그룹 생성

   인바운드 그룹에 규칙을 추가한다.

   > 1. SSH, TCP, 포트 22로 서버에 접속할 IP 추가 (터미널에서 pem 사용하여 접근 시 필요)
   > 2. 사용자 지정 TCP, TCP, 포트 8080(\혹은 다른 프로젝트 기본 포트)으로 `0.0.0.0/0, ::/0` 입력
   > 3. HTTPS, TCP, 포트 443으로 `0.0.0.0/0, ::/0` 입력

4. 인스턴스 생성, 키 페어 생성

5. 고정 IP 할당해야 함 

   > 1. EIP(탄력적 IP) 탭으로 들어간 후 새 주소를 할당받는다.
   > 2. 해당 EIP를 선택하고 우리의 서버 인스턴스와 주소 연결한다.

6. 이제 터미널로 접속할 때에는 이 아이피를 사용한다.

   ```shell
   ssh -i [pem 키의 위치] [EC2의 EIP 주소]
   ```

   [윈도우에서 접속](https://m.blog.naver.com/alice_k106/220882708567)

7. [pem키 접속 간소화 + 추가로 해야 할 일](https://ict-nroo.tistory.com/135)

   - java 8 설치
   - Timezone 서울로 변경
   - Hostname 변경, 등록

8. AWS RDS 인스턴스를 생성하고 설정한다.

   - EC2 내부에 DB가 존재하는 구조를 지양하기 위해 RDS 서비스를 사용한다.
   - 참고 :  [RDS 인스턴스 생성 및 EC2에서 접근하기](https://victorydntmd.tistory.com/337)

<br>

### 서버에 프로젝트 배포하기

1. 프로젝트 clone 받기

   > step1 폴더는 다른 이름으로 써도 됨

   ```shell
   sudo yum install git
   git --version
   mkdir ~/app && mkdir ~/app/step1
   cd ~/app/step1
   git clone [우리의 레포 주소]
   cd [우리의 레포]
   ```

2. 테스트 코드 수행 및 검증 (테스트 코드가 있는 경우)

   ```shell
   chmod +x ./gradlew /* 실행 거부될 시 */
   ./gradlew test
   ```

3. 배포 스크립트 만들기

   다음 과정을 모두 수행하는 스크립트를 만들자

   - git clone 혹은 git pull을 통해 새 버전의 프로젝트 받기
   - gradle이나 maven을 통해 프로젝트 테스트와 빌드
   - EC2 서버에서 해당 프로젝트 실행 및 재실행

   스크립트 파일을 생성한다.

   ```sh
   vi ~/app/step1/deploy.sh
   ```

   생성한 스크립트 파일에 다음 코드를 추가한다.

   ```shell
   REPOSITORY=/home/ec2-user/app/step1
   GITHUB_REPOSITORY_NAME=[우리의 프로젝트 레포 이름]
   PROJECT_NAME=[우리의 프로젝트 이름]
   
   cd $REPOSITORY/$GITHUB_REPOSITORY_NAME
   
   echo "> Git Pull"
   
   git pull
   
   echo "> 프로젝트 Build 시작"
   
   ./gradlew build
   
   echo "> step1 디렉토리로 이동"
   
   cd $REPOSITORY
   
   echo "> Build 파일 복사"
   
   cp $REPOSITORY/$GITHUB_REPOSITORY_NAME/build/libs/*.jar $REPOSITORY/
   
   echo "> 현재 구동중인 애플리케이션 pid 확인"
   
   CURRENT_PID=$(pgrep -f ${PROJECT_NAME}.*.jar)
   
   echo "현재 구동 중인 애플리케이션 pid: $CURRENT_PID"
   
   if [ -z "$CURRENT_PID"]; then
   	echo "> 현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다."
   else
   	echo "> kill -15 $CURRENT_PID"
   	kill -15 $CURRENT_PID
   	sleep 5
   fi
   
   echo "> 새 애플리케이션 배포"
   
   JAR_NAME=$(ls -tr $REPOSITORY/ | grep jar | tail -n 1)
   
   echo "> JAR name: $JAR_NAME"
   
   
   nohup java -jar \
   	-Dspring.config.location=classpath:/application.properties, \
   	classpath:/application-real.properties \
   	[, 레포 밖의 적용할 보안 필요한 properties 파일 경로들 입력] \
   	-Dspring.profiles.active=real \
   $REPOSITORY/$JAR_NAME 2>&1 &
   ```

   > nohup는 사용자가 터미널 접속을 끊어도 애플리케이션이 계속 구동되도록 해줌
   >
   > `-Dspring.profiles.active=real`는 실제 운영 환경의 설정인 `application-real.properties`를 활성화해줌

   스크립트에 실행 권한 추가 후 실행, 로그 확인

   ```shell
   chmod +x ./deploy.sh
   ./deploy.sh
   vi nohup.out
   ```

   

   **더 자세한 설명**

   1. [AWS EC2 & RDS 구축하기](https://jojoldu.tistory.com/259)

   2. [EC2에 배포하기](https://jojoldu.tistory.com/263?category=635883)

