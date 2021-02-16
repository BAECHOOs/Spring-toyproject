# DAO(Data Access Object)
* `DB의 데이터에 접근`하기 위한 객체
* 단일 데이터의 접근 및 갱신을 의미 -> Service: 하나 이상의 DAO를 이용해 비지니스 로직을 처리, 트랜잭션의 단위
* 사용할 DB, 어떤 드라이브와 로그인 정보를 사용할 것인가 등을 표시

# DTO(Data Transfer Object)
* 다른 말로 VO(Value Object)
* 계층(컨트롤러, 뷰, 비즈니스 계층 등)간 `데이터 교환(전송)`을 위한 자바 빈
* `로직을 가지지 않음`: 속성과 속성에 접근하기 위한 getter, setter가 있고 추가적으로 toString(), equals() 가능
## DTO가 필요한 이유
* Entity에 Setter를 두지 않는 것이 좋음
* 하지만, Setter가 없으면 Controller에서 값의 할당이 되지 않기 때문에 `DTO를 만들고 Setter를 두어 값을 처리`
* Entity에 Setter가 있더라도 이를 `request / response`에서 사용하지 않는 것이 좋음 -> Entity는 데이터 갱신에 신중해야 하지만 `request / response`는 수정할 일이 많음
## DTO 예시
* Entity 클래스
```java
@Getter
@NoArgsConstructor
@Entity
public class Order extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long order_id;

    @Column(nullable = false)
    private Long user_id;

    @Column(nullable = false)
    private Long product_id;

    @Column(nullable = false)
    private Long purchase_check;

    @Builder
    public Order(Long user_id, Long product_id, Long purchase_check) {
        this.user_id = user_id;
        this.product_id = product_id;
        this.purchase_check = purchase_check;
    }
}
```
* DTO 클래스
```java
@Getter
@Setter
@NoArgsConstructor
public class OrderDto {

    private Long user_id;
    private Long product_id;
    private Long purchase_check;
}
```