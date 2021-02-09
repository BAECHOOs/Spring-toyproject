# MySQL 스키마의 참고 사항

## 1. `utf8`이 아닌 `utf8mb4`를 사용해야 합니다.
* `utf8`은 `utf8mb3`의 줄임말인데 현재의 MySQL 버전(8.0)에서는 `utf8`이 `utf8mb4`를 가리키게 되어 `utf8mb3` 또는 `utf8mb4`를 명시해야 합니다.
* 저희의 스키마에서는 `utf8mb4`를 사용했습니다.
## 2. MySQL에서의 Boolean은 TINYINT 입니다.
* MySQL에서는 Bool형이 따로 존재하지 않아서 TINYINT를 사용합니다.
* Insert시에 `true` 또는 `false`를 넣으면 DB에는 `1` 또는 `0`으로 저장됩니다.
## 3. 릴레이션에서의 튜플 생성시간과 튜플 갱신시간을 지정하는 방법은 아래와 같습니다.
```
`upload_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
`update_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
```
* 테이블을 생성할 때, 속성을 위와 같이 설정하면 됩니다.
* 형식은 `DATETIME`을 사용했습니다.
* 처음 튜플 `INSERT` 시 `upload_date`와 `update_date`가 `0000-00-00 00:00:00(년-월-일 시-분-초)` 형식으로 자동 저장됩니다.
* 튜플을 `UPDATE`하면 `update_date`만 `0000-00-00 00:00:00(년-월-일 시-분-초)` 형식으로 자동 변경됩니다.
* 예시
  * 전체 sql은`spring-team/sql`에서 볼 수 있습니다.
```
Insert into Product (product_id, user_id, name, price, description, picture_url, views, on_sale, like_count) values(0, 8, '', 4000, '', '', 0, true, 0);

Update Product set description = '' where product_id=1;
```
