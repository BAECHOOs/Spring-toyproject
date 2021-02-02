# Thymeleaf 정리

지금의 정리는 <a href="https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html" target="_blank"> thymeleaf 공식 문서 </a>를 참고하였습니다.



스프링 프레임워크의 View에 사용됩니다. 기존에 대표적으로 사용되는 jsp와 thymeleaf가 어떻게 다른지 코드로 확인해보겠습니다.

<br>

##### *jsp*

```html
<form:inputText name="userName" value="${user.name}" />
```

공식 문서의 설명으로는 jsp로 했을 때는, 브라우저에서 표시할 수 없는 코드가 포함될 수 있다고 합니다.

<br>

##### *thymeleaf*

```html
<input type="text" name="userName" value="James Carrot" th:value="${user.name}" />
```

익숙한 문법으로 디자이너와 협업할 때도 장점이 있습니다.

<br>

<br>

## 텍스트 사용하기

#### 1) Variable Expressions: `${...}`

```html
<p>Today is: <span th:text="${today}">13 february 2011</span></p>
```

서버에서 `today` 값을 넘겨주면, `13 february 2011` 가 넘겨받은 `today` 값으로 대체됩니다.



<br>

#### 2) Selection Variable Expressions: `*{...}`

```html
<div th:object="${session.user}">
    <p>Name: <span th:text="*{firstName}">Sebastian</span>.</p>
    <p>Surname: <span th:text="*{lastName}">Pepper</span>.</p>
    <p>Nationality: <span th:text="*{nationality}">Saturn</span>.</p>
</div>
```

선택된 객체가 없다면, `${...}` 와 동일합니다. 위처럼 사용될 수 있습니다. (아래의 ***<u>각종 팁</u>*** 2번 참고)



<br>

#### 3) Message Expressions: `#{...}`

```html
<p th:text="#{home.welcome}">Welcome to our grocery store!</p>
```

여러 나라의 언어로 텍스트를 지원하고 싶을 때, 위처럼 `home.welcome` 에 여러 나라 버전으로 텍스트를 준비해두면, 다국어를 지원할 수 있습니다. 

cf) 만약에 `tag` 안에 또 `tag` 를 넣고 싶다면, `th:text` 대신 `th:utext` 를 사용하면 안전하게 사용할 수 있습니다.



<br>

#### 4) Link URL Expressions: `@{...}`

```html
<link rel="stylesheet" type="text/css" media="all" 
          href="../../css/gtvg.css" th:href="@{/css/gtvg.css}" />
```

html파일을 그냥 열면 `href` 가 적용되고, 서버에서는 `th:href` 를 사용합니다.

```html
<a href="details.html" th:href="@{/order/details(orderId=${o.id})}">view</a>
```

그리고 위와 같이 파라미터도 넣을 수 있다.



<br>

#### 5) Fragment Expressions: `~{...}`

##### **/WEB-INF/templates/footer.html**

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
  <body>
 
    <div th:fragment="copy">
      &copy; 2011 The Good Thymes Virtual Grocery
    </div>
  
  </body>
</html>
```

##### home.html

```html
<body>

  ...

  <div th:insert="~{footer :: copy}"></div>
  
</body>
```

레이아웃을 짤 때, 중복되는 코드를 이것으로 막을 수 있습니다. 



<br>

<br>

## if, for, switch문

#### 1) if문

#####  (1)

```html
<div th:if="${user.isAdmin()} == false"> ...
```

if문이 참이면 `div` 가 살아있고, 아니면 없어집니다.

```html
<div th:if="${variable.something} == null"> ...
```

`null` 체크도 가능합니다.



#####  (2)

```html
<tr th:class="${row.even}? 'even' : 'odd'">
  ...
</tr>
```

`condition` ? `true일때` : `false일때`  형태로도 사용할 수 있습니다.

```html
<tr th:class="${row.even}? 'alt'">
  ...
</tr>
```

참인 경우만 설정할 수도 있습니다.





<br>

#### 2) for문

우선 자바에서 `allProducts` 라는 리스트를 `prods` 에 담았다고 했을 때,

```java
List<Product> allProducts = productService.findAll();

WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
ctx.setVariable("prods", allProducts);
```

아래처럼 `th:each` 를 사용해서 for문을 사용할 수 있습니다.

```html
<tr th:each="prod : ${prods}">
    <td th:text="${prod.name}">Onions</td>
    <td th:text="${prod.price}">2.41</td>
    <td th:text="${prod.inStock}? #{true} : #{false}">yes</td>
</tr>
```





#### 3) switch문

```html
<div th:switch="${user.role}">
  <p th:case="'admin'">User is an administrator</p>
  <p th:case="#{roles.manager}">User is a manager</p>
  <p th:case="*">User is some other thing</p>
</div>
```

`th:switch`와 `th:case` 를 이용해서 사용할 수 있고, `*` 으로 default 값도 설정해줄 수 있습니다.



<br>

<br>

## 연산자

`gt` (`>`), `lt` (`<`), `ge` (`>=`), `le` (`<=`), `not` (`!`). Also `eq` (`==`), `neq`/`ne` (`!=`).







<br>

<br>

## 각종 팁

#### 1) 문자열 `|` 이용하기

```html
<span th:text="'Welcome to our application, ' + ${user.name} + '!'">
```

위와 같은 경우, `|` 을 이용해서 다음과 같이 사용할 수 있다.

```html
<span th:text="|Welcome to our application, ${user.name}!|">
```





<br>

#### 2) 네이밍을 할 때 `부모-자식` 관계를 다음과 같이 나타낼 수 있다.

##### ☝ `th:object` 사용

```html
<div th:object="${session.user}">
   <p>Name: <span th:text="*{firstName}">Sebastian</span>.</p>
   <p>Surname: <span th:text="*{lastName}">Pepper</span>.</p>
   <p>Nationality: <span th:text="*{nationality}">Saturn</span>.</p>
</div>
```

##### ✌ `th:object` 미 사용

```html
<div>
   <p>Name: <span th:text="${session.user.firstName}">Sebastian</span>.</p>
   <p>Surname: <span th:text="${session.user.lastName}">Pepper</span>.</p>
   <p>Nationality: <span th:text="${session.user.nationality}">Saturn</span>.</p>
</div>
```





#### 3) th:attr은 권장되지 않습니다.

```t
Thymeleaf는 귀하의 의견에 동의하므로 th : attr이 템플릿에서 거의 사용되지 않습니다. 일반적으로 특정 태그 속성을 설정하는 작업이있는 다른 th : * 속성을 사용합니다 (th : attr과 같은 속성뿐 아니라).
```

th:attr을 통해서 value나 action값을 설정하지 말고, 직접 해당 속성을 설정하는 것을 권장합니다.

1. `th:value`

2. `th:action`

3. `th:href`

4. `th:classappend` (class추가)

5. `th:checked`

   



#### 4) 샘플 데이터 넣기

```html
<table>
   <tr th:each="x : ${xs}">
     ...
   </tr>
   <!--/*-->
   <tr>
     ...
   </tr>
   <tr>
     ...
   </tr>
   <!--*/-->
</table>
```

<!--/*--> 안에 샘플 데이터를 넣으면 됩니다. 실제 서버를 돌릴 때는, 주석 처리 됩니다.





#### 5) th:block으로 태그 없이 서버의 데이터를 사용하기

```html
<table>
  <th:block th:each="user : ${users}">
    <tr>
        <td th:text="${user.login}">...</td>
        <td th:text="${user.name}">...</td>
    </tr>
    <tr>
        <td colspan="2" th:text="${user.address}">...</td>
    </tr>
  </th:block>
</table>
```

`th:block` 부분은 실제 서버를 돌릴 때는 없어집니다.



