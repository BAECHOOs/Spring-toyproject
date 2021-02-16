### View 변경 바로 확인하기

- 기존에는 html 파일을 변경하면 이를 확인하기 위해 서버를 재시작했습니다.
- 이 과정이 번거로우셨다면 다음 방법을 추천합니다.

1. `spring-boot-devtools` 라이브러리를 추가합니다.

   프로젝트 파일에는 추가되어 있습니다.

2. Ctrl + Shift + A를 누르고, registery를 쳐서 들어갑니다.

   `compiler.automake.allow.when.app.running`의 value가 true가 되도록 체크합니다.

3. Settings > compiler에 들어갑니다.

   Ctrl + Shift + A를 누르고, compiler를 쳐도 들어갈 수 있습니다.

   `Build project automatically`에 체크를 합니다.

   ![image](https://user-images.githubusercontent.com/41130448/108048666-a7538b00-708a-11eb-826f-978436dc10fd.png)



- 서버를 시작하고 localhost:8080에 들어갑니다.

  view를 수정한 후 build > Recompile을 누르거나 Ctrl + Shift + F9을 누릅니다.

  localhost 페이지에서 새로고침을 합니다.

  변경사항이 반영되어 있는 것을 볼 수 있습니다.