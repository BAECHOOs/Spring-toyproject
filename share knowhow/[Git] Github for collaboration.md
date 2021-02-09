# git으로 협업하기

여러분 작업 전에 꼭 git pull 하세요~~~ 😎





일단 2020년 10월부터 기본브랜치가 master가 아닌, main으로 변경되었습니다.

(다시말해, 새로운 레파지토리를 만들면 기존에 익숙했던 master라는 이름이 아니라, main이라는 이름을 보게 됩니다.)

https://blog.outsider.ne.kr/1503







### 1. 세팅

#### 1) 줄바꿈으로 발생하는 문제 해결

**윈도우**

```shell
git config --global core.autocrlf true
```

**맥**

```shell
git config --global core.autocrlf input
```



#### 2) 자주쓰는 명령어 설정

*이건 필수가 아닙니다만, 전 이렇게 써요~* 

```shell
git config --global alias.st status
```

**git status 길게 안 적어도, git st 로 동일한 결과를 얻을 수 있습니다.**

<br>



### 2. 협업하기 (pull request, merge)

- pull request : merge전에 팀원들에게 리뷰 받는것 (master브랜치 보호 용도)
- merge : 브랜치를 하나로 모으는 것



1) merge 전

![image](https://user-images.githubusercontent.com/42775225/105865381-3ba37280-6036-11eb-8166-9bb6ec7de794.png)

2) merge 후

![image](https://user-images.githubusercontent.com/42775225/105865403-3fcf9000-6036-11eb-8ea9-9c45677e728e.png)

source: https://backlog.com/git-tutorial/kr/stepup/stepup1_1.html






### [ 리더 수행 순서 ]

1. 팀원들을 프로젝트의 collaborator로 초대합니다.

   : settings -> manage access -> invite a collaborator 

2. main 브랜치에 규칙을 추가합니다.

   <br>

   ![branch1](https://user-images.githubusercontent.com/42775225/105868845-f123f500-6039-11eb-8bce-4227ef1ce68e.PNG)

   

   <br>

   다음 내용은, 누군가 pull request를 했을 때, 한 명만 리뷰를 한다면, merge할 수 있도록 하며, 이는 admin에게 동일하게 적용되도록 설정한 것입니다.

   <br>

   

   ![branch2](https://user-images.githubusercontent.com/42775225/105868848-f2552200-6039-11eb-9e7a-2e198f3dc449.PNG)

   



### [ 모든 팀원 작업 순서 ]

*프로젝트 repository를 local로 클론해온 상황(main 브랜치)에서 시작합니다.*

#### 1) 개인 브랜치를 만들어 작업을 완료합니다. ✅

```shell
git branch [브랜치명]
git checkout [브랜치명]

/* 작 업 수 행 */

git add .
git commit -m '메세지'
git push origin [브랜치명]
```



#### 2) pull request 합니다. ✅

![image](https://user-images.githubusercontent.com/42775225/105870192-60e6af80-603b-11eb-913c-82fd22ba80d2.png)
![image](https://user-images.githubusercontent.com/42775225/105870219-65ab6380-603b-11eb-8568-ebda07759725.png)
![image](https://user-images.githubusercontent.com/42775225/105870243-6a701780-603b-11eb-9813-0f5edb240525.png)
![image](https://user-images.githubusercontent.com/42775225/105870256-6e039e80-603b-11eb-92c5-bdfe0ddf6cf9.png)





#### 3) merge 합니다. ✅

![image](https://user-images.githubusercontent.com/42775225/105870504-af944980-603b-11eb-8e0b-59f02d19c304.png)
![image](https://user-images.githubusercontent.com/42775225/105870517-b327d080-603b-11eb-9036-b222e6e98816.png)
![image](https://user-images.githubusercontent.com/42775225/105870527-b622c100-603b-11eb-9d9b-b14462a7b240.png)
![image](https://user-images.githubusercontent.com/42775225/105870541-b9b64800-603b-11eb-9148-8b39fadb4224.png)
![image](https://user-images.githubusercontent.com/42775225/105870553-bcb13880-603b-11eb-85b4-838c72a1c16f.png)







### cf) 알아두면 좋은 명령어

#### 1) add 취소

```shell
git rm --cached *
```



#### 2) commit 취소

```shell
git reset HEAD^
```

(바로 직전 commit을 취소하는 경우입니다.)

