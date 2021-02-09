## 📜 IntelliJ DB Navigator 사용하기

<br>

아래 설명은 제가 했던 방법이며, 컴퓨터 환경에 따라 설정이 달라질 수도 있습니다 !

<br>

### 플러그인 설치하기

![image](https://user-images.githubusercontent.com/41130448/107137539-e5490480-6950-11eb-8fb5-95be57519878.png)

인텔리제이 플러그인에서 Database Navigator를 설치합니다.

![image](https://user-images.githubusercontent.com/41130448/107137602-5c7e9880-6951-11eb-961d-b095f9166fa4.png)

좌측에 이런 선택지가 생깁니다! 

<br>

### TimeZone 설정하기

우리의 프로젝트는 Asia/Seoul의 타임존을 갖지만 MySQL의 기본 설정은 그렇지 않을 확률이 높습니다. 그런 경우 TIMESTAMP 자료형 때문에 연결에 오류가 생깁니다. 이를 해결해봅시다.

먼저 MySQL 에서 다음 쿼리를 실행합니다.

```sql
SET GLOBAL time_zone='Asia/Seoul';
SET time_zone='Asia/Seoul';
```

그런데 

```
Error Code: 1298. Unknown or incorrect time zone: 'Asia/Seoul'
```

이런 에러가 뜬다면, 타임존 관련 데이터가 없는 것입니다.

[이 링크](https://dev.mysql.com/downloads/timezones.html)로 이동해서 

![image](https://user-images.githubusercontent.com/41130448/107137632-bf702f80-6951-11eb-925e-c5eb7dcdb262.png)

이 파일을 받아줍시다.

다 받았으면 mySQL에서 파일을 연 후에

```sql
use mysql;
```

을 먼저 실행해 주시고, 파일에 있는 쿼리를 몽땅 실행합니다.

<br>

다 되었으면 다시 위로 올라가서 Asia/Seoul로 설정하는 쿼리를 다시 실행합시다.

<br>

### IntelliJ에서 연결하기

![image](https://user-images.githubusercontent.com/41130448/107137656-0d853300-6952-11eb-96c2-bddb413eb166.png)

요기를 누르고, MySQL을 선택하면 창이 하나 뜹니다.

![image](https://user-images.githubusercontent.com/41130448/107137706-69e85280-6952-11eb-9c36-59a0511f0ec4.png)

디비 이름을 쓰고, localhost:3306으로 설정합니다.

User/password는 mysql을 설치할 때 초기에 만들었던 계정의 아이디/비밀번호를 입력합니다.

그리고 Test Connection을 눌러서 연결이 되는지 확인합니다.

![image](https://user-images.githubusercontent.com/41130448/107137712-79679b80-6952-11eb-90b3-015285eabd97.png)

이렇게 뜨면 잘 연결된 것입니다 !

![image](https://user-images.githubusercontent.com/41130448/107137726-97cd9700-6952-11eb-9be1-60cadc5f9351.png)

이제 여기서 콘솔 작업을 하고 스키마도 확인할 수 있습니다. 👼🏻 Yay~

<br>

### 추가사항

나중에 서버에 DB를 올린 후에도 이를 통해 원격으로 연결할 수 있답니다~