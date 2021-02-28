## 도메인 클래스 참고사항

<br>

### 📌 라이브러리를 새로 추가했습니다

1. **`lombok` 라이브러리**

   - 자주 사용하는 Getter, Setter, 기본 생성자, toString 등을 자동 생성해줍니다.

   - 클래스 위에 작성합니다.

   - `@Getter`, `@Setter` 어노테이션

     클래스 내에 선언된 모든 필드의 get, set 메소드를 생성해줍니다.
     
   - `@NoArgsConstructor` 어노테이션

     기본생성자를 자동으로 추가합니다.
     
     `public ClassName(){ }`과 같은 효과를 가지고 있습니다.
     
   - `@Builder` 어노테이션
   
     - 해당 클래스의 빌더 패턴 클래스를 생성합니다.
     - 생성자 위에 작성합니다.
     - 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함합니다.
   
2. **`JPA` 라이브러리**

   - `@Entity` 어노테이션

     - DB 테이블과 링크될 클래스임을 나타냅니다. 

     - 클래스의 위에 써줍니다.
     - 이걸 써준 클래스는 Entity 클래스라고 부릅시다.

     - 기본값으로는 다음과 같이 카멜케이스 클래스 → 언더스코어 테이블로 매칭합니다.

       `SalesManager.java` → `sales_manager table`

   - `@Id` 어노테이션

     - 해당 테이블의 PK 필드를 나타냅니다. 
     - PK로 사용할 변수 위에 써줍니다.

   - `@GeneratedValue` 어노테이션

     - PK의 생성 규칙을 나타냅니다.

     - `@GeneratedValue(strategy = GenerationType.IDENTITY)`와 같이 선언할 때 `GenerationType.IDENTITY` 옵션을 걸어주면 `auto_increment`의 효과를 갖습니다.

   - `@Column` 어노테이션

     - 테이블의 컬럼을 나타냅니다.

     - 굳이 선언 안해도 해당 클래스의 필드는 모두 컬럼이 됩니다.

     - 그런데도 선언하는 이유는 기본값 외에 추가로 변경할 옵션이 있을 때 씁니다.

       > 예를 들면 문자열의 사이즈를 기본값(255)가 아닌 500으로 늘리고 싶거나 타입을 TEXT로 변경하고 싶은 경우가 있습니다.
       >
       > ```java
       > @Column(columnDefinition = "TEXT", length = 500)
       > ```
   
3. **`BaseTimeEntity.java` 클래스를 새로 만들었습니다.**

   - 목적은 JPA Auditing으로 생성시간과 수정시간을 자동화 하는 것입니다.

     entity에서는 보통 데이터 생성, 수정 시간을 포함합니다. 이 때문에 매번 DB에 삽입 및 갱신 전에 날짜 데이터를 등록하거나 수정하는 코드가 여기저기 쓰이게 되는데, 이런 단순 반복을 지양하기 위해 사용합니다.

   - 사용하는 방법은 Entity 클래스를 만들때 `BaseTimeEntity` 클래스를 상속받으면 됩니다.

     ```java
     public class ClassName extends BaseTimeEntity {}
     ```

   - `@MappedSuperclass` 어노테이션

     JPA Entity 클래스들이 BaseTimeEntity를 상속할 경우 필드들(createdDate, modifiedDate)도 컬럼으로 인식하도록 합니다.

   - `@EntityListeners(AuditingEntityListener.class) `어노테이션

     BaseTimeEntity 클래스에 Auditing 기능을 포함시킵니다.

   - `@CreatedDate` 어노테이션

     Entity가 생성되어 저장될 때 시간이 자동 저장됩니다.

   - `@LastModifiedDate `어노테이션

     조회한 Entity의 값을 변경할 때 시간이 자동 저장됩니다.

   - 미리 만든 BaseTimeEntity를 상속받으면 추가될 엔티티의 등록/수정일 고민을 할 필요가 없어집니다.

   > User, Product, Order 모두 사용하면 좋을 것 같고, Like는 필요 없을지 모르겠는데 일단 상속 시켜 놓았습니다.

<br>

### 🔍 권장사항

1. Entity 클래스에는 Setter 메소드를 만들지 않는 것이 좋습니다.
   - 왜냐하면 무작정 getter/setter 생성시 클래스의 인스턴스 값들이 언제 어디서 변해야 하는지 코드상으로 명확하게 구분하기 어렵기 때문에 차후 기능을 변경할 일이 생길 시 복잡해집니다.
   - 해당 필드의 값 변경이 필요하다면, 그 목적과 의도를 명확히 나타낼 수 있는 메소드 추가해야 합니다.
2. Setter가 없다면 어떻게 값을 채워서 DB에 삽입할까요?
   - 기본적 구조는 생성자를 통해 최종값을 채운 후 DB에 삽입하는 것 입니다.
   - 값 변경이 필요하면 해당 이벤트에 맞는 public 메소드를 호출해서 변경합니다.
   - 권장하는 바는 `@Builder` 어노테이션을 통해 제공되는 빌더 클래스를 사용하는 것입니다. 이런 경우 어느 필드에 어떤 값을 채워야 하는지 쉽게 인지할 수 있습니다.