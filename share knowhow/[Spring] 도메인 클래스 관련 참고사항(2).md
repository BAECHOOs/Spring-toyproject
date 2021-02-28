# 프로젝트의 도메인 클래스에 관한 정리
* 설명에 필요한 속성만 뽑았습니다.

## 1. 어노테이션 정리
```java
@Getter
@NoArgsConstructor
@Entity
@Table(schema = "baechoo", name = "Product")
@DynamicInsert
public class Product extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    @ColumnDefault("NULL")
    private String description;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Builder(builderClassName = "ProductBuilder", builderMethodName = "ProductBuilder")
    public Product(String title, String description, User user) {
        this.title = title;
        this.description = description;
        this.user = user;
    }
}
```
```java
@Data
@Entity
@Table(schema = "baechoo", name = "User")
@DynamicInsert
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @OneToMany(mappedBy = "user")
    private List<Product> products;

    @Builder
    public User(String nickname, String email, String password, Boolean enabled, Role role){
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.roles.add(role);
    }
}
```
* `@Entity`
  * 해당 클래스가 객체임을 명시합니다.
* `@Table`
  * 테이블의 스키마와 이름을 지정합니다.
  * 사용하지 않으면 클래스의 이름이 테이블의 이름으로 지정됩니다.
* `@DynamicInsert`
  * DB에 튜플을 저장할 때, 값을 지정하지 않은 속성은 `@ColumnDefault`로 지정한 Default 값으로 저장합니다.
  * 해당 어노테이션을 사용하지 않으면, 기본 값으로 Null로 저장됩니다.
* `@Id`
  * 기본 키에 사용합니다.
* `@GeneratedValue`
  * 기본 키의 값을 생성하는 기준을 결정합니다.
  * `@Id`가 있는 속성에서만 사용할 수 있습니다.
* `@Column`
  * 컬럼의 이름, null 가능 여부 등을 결정할 수 있습니다.
* `@ColumnDefault`
  * 컬럼의 기본 값을 설정합니다.
  * `@ColumnDefault`을 사용해야 적용됩니다.

## 2. 참조 관계 설정
```java
// Product 엔티티
@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
@JoinColumn(name="user_id")
private User user;
```
```java
// User 엔티티
@OneToMany(mappedBy = "user")
private List<Product> products;
```
### `@ManyToOne`
* `N:1`의 관계를 지정합니다.
* 외래키를 지정할 때 사용합니다.
* `Product` 테이블에 이름이 `user_id`이고 `User` 테이블의 주키를 가리키는 외래키를 추가합니다.
### `@OneToMany`
* `1:N`의 관계를 지정합니다.
* `@ManyToOne`만 사용하고 `@OneToMany`는 사용하지 않아도 관계 매핑은 진행됩니다. `@OneToMany`는 해도 되고 안해도 됩니다.
* `mappedBy`의 이름은 다른 테이블에서 참조할 때 사용한 이름과 같은 것을 사용합니다.
* 위의 예시에서는 Product에서는 `private User user`로 참조했으므로 `mappedBy = "user"`로 해야 합니다.
### `fetch` 타입
* `LAZY`
  * 연관 관계에 매핑되어 있는 엔티티의 DB에는 접근하지 않고, 실제 사용할 때 조회합니다.
  * 예를 들어, Product를 조회할 때, User는 조회하지 않습니다.
* `EAGER`
  * 연관 관계에 매핑되어 있는 엔티티도 함께 조회합니다.
  * 예를 들어, Product를 조회할 때, User도 조회합니다.
  * 성능 문제를 일으킬 수 있어 `LAZY`가 추천되고 있습니다.
* 기본 값은 `LAZY` 입니다.

## 3. 복합 (기본)키 설정
```java
@NoArgsConstructor
@AllArgsConstructor
public class UserRolePK implements Serializable {
    private Long user;
    private  Long role;
}
```
```java
@IdClass(UserRolePK.class)
@DynamicInsert
public class UserRole {
    @Id
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Id
    @ManyToOne(targetEntity = Role.class, fetch = FetchType.LAZY)
    @JoinColumn(name="role_id")
    private Role role;
}
```
* `@IdClass` 어노테이션을 사용합니다.
  * 다른 방법도 있으니 자세한 사항은 인터넷 검색을 활용하시면 됩니다.
* 기본키를 관리하는 클래스 `UserRolePK`를 만들어 사용합니다.
  * 이 클래스는 `Serializable`를 구현해야합니다.
* 예시 설명
  * `UserRole`은 User의 주키를 가리키는, 이름이 `user_id`인 외래키를 갖습니다. 이는 곧 `UserRole` 테이블의 주키이기도 합니다.
  * `UserRole`은 Role의 주키를 가리키는, 이름이 `role_id`인 외래키를 갖습니다. 이는 곧 `UserRole` 테이블의 주키이기도 합니다.

