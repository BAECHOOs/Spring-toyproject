# Spring Security

<br>

### 인증(Authentication) vs 인가(Authorization)

<strong>인증 (Authentication)</strong>을 통해 <u>사용자를 식별</u>하고, <strong>인가(Authorization)</strong>를 통해 <u>시스템 자원에 대한 접근을 통제</u>한다.

- 인증 (Authentication)의 예시

  회사 건물에 들어가기 위한 사원증 카드

- 인가(Authorization)의 예시

  직급과 직무에 따라 부여된 권한이 다름. 열람할 수 있는 문서의 종류 제한

<br>

### 시큐리티 필터

- 먼저 <strong>서블릿 필터(javax.servlet.Filter)</strong>로 개발한 **시큐리티 필터**를 이해해보자.
- 서블릿 필터는 
  1. 클라이언트의 요청을 가로채서 서블릿의 수행 전후에 전처리나 후처리를 시행하거나
  2. 요청을 리다이렉트한다.
- 서블릿 필터는 필터 한 개당 하나의 기능을 처리한다.
- 따라서 여러 기능이 필요하면 필터를 여러개 만들어 체인 형태를 형성하여 쓴다.

<img src="https://user-images.githubusercontent.com/41130448/106468475-51ef7980-64e1-11eb-91bd-eab3722de687.png" alt="image" width="zoom:67%;" />

- **스프링 시큐리티**는 요론 식으로 **필터 체인**을 통해 다양한 기능을 제공한다.

  > 시큐리티 필터의 예시
  >
  > 앞 단에서 인증되지 않은 사용자가 특정 URL 요청 시 로그인 화면을 보여주는 기능

- 스프링 시큐리티에서 제공하는 중요한 필터를 알아보자.

  얘네는 1번부터 8번까지 순서대로 동작한다.

  1. `SecurityContextPersistenceFilter`

     `SecurityContextRepository`에서 `SecurityContext` **객체를 로딩**하여 `SecurityContextHolder`에 **저장**하고 요청 처리가 끝나면 **제거**하는 필터

  2. `LogoutFilter`

     지정한 경로의 요청이 들어오면 **로그아웃**하고, **지정한 페이지로 이동**. 이후의 필터는 진행하지 않는다.

  3. `UsernamePasswordAuthenticationFilter`

     로그인 요청이 들어오면, **아이디/비밀번호 기반의 인증**을 수행한다. 

     - 인증에 성공하면 지정한 페이지로 이동, 

     - 인증에 실패하면 로그인 화면을 보여줌

  4. `DefaultLoginPageGeneratingFilter`

     로그인 요청이 들어오면 기본으로 제공하는 **로그인 화면을 출력**, 이후 필터는 진행하지 않음

  5. `AnonymousAuthenticationFilter`

     이 필터가 실행되는 시점까지 사용자가 인증을 받지 못했다면, 임의 사용자에 해당하는 `Authentication` 객체를 생성하여 `SecurityContext`에 설정함.

     - 생성된 `Authentication`의 아이디는 `"anonymousUser"`
     - 권한은 `"ROLE_ANONYMOUS"`
     - 상태값은 인증되지 않은 상태

  6. `SessionManagementFilter`

     세션 타임아웃, 동시 접근 제어, 세션 고정 공격 등을 **처리**함

  7. `ExceptionTranslationFilter`

     `FilterSecurityInterceptor`에서 발생한 **예외를** 웹에 맞는 응답으로 **변환**

     예) 403 코드 → 로그인 페이지로 이동하는 작업

  8. `FilterSecurityInterceptor`

     현재 사용자가 지정한 경로에 **접근할 수 있는지 여부를 검사**.

     - 권한이 있으면 보안 필터 통과, 자원 접근 가능
     - 권한이 없으면 예외 발생

<br>

### 스프링 시큐리티의 동작 원리

- 위에서 스프링 시큐리티는, 시큐리티 필터들의 상호작용 (필터 체인)을 통해 처리된다고 했다.

- 구성 필터 중에서 가장 중요한 필터가 두 개가 있다.

  1. (a) `UsernamePasswordAuthenticationFilter`가 제일 중요하다.

     왜냐면 얘는 실제 사용자가 입력한 인증 정보를 통해 인증 처리하기 때문

  2. (b) `FilterSecurityInterceptor`가 두번째로 중요하다.

     왜냐면 얘는 인증에 성공한 사용자가 해당 리소스에 접근할 권한이 있는지 검증하기 때문 

- 크게 보면 이런 플로우로 들어간다.

  요청 → **(a) 인증 관리 필터** → **(b) 권한 관리 필터** → 리소스

  > 사용자가 리소스 요청시
  >
  > **→ (a)가 사용자 요청을 가로챔**
  >
  > → (a)가 자신과 관련된 필터를 이용하여 사용자 인증을 처리함
  >
  > → 인증되지 않은 사용자라면, 인증에 필요한 정보를 요청
  >
  > → 사용자가 인증 절차(로그인)를 통해 인증 정보 입력
  >
  > → 파일이나 DB에 저장되어 있는 정보를 읽고, 사용자가 입력한 인증 정보를 검증
  >
  > ​	→ 인증 실패시 다시 요청
  >
  > → 인증 통과시 사용자가 요청한 리소스로 요청을 전달한다.
  >
  > **→ (b)가 다시 이 요청을 가로챔**
  >
  > → (b)가 권한 관련 필터를 이용하여 리소스 접근 권한을 검증
  >
  > ​	리소스별 권한 목록을 참조하여 검증한다.
  >
  > ​	→검증 실패시 권한 없음 페이지 전송 or 접근 차단

<img src="https://user-images.githubusercontent.com/41130448/106470901-684b0480-64e4-11eb-9fe2-50630fa81181.png" alt="image" style="zoom:80%;" />

