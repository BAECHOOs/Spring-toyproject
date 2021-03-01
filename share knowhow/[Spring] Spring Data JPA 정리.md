# Spring Data JPA

## Spring Data JPA의 개념
```java
public interface EntityRepository extends JpaRepository<Entity, IDType> { }
```
* Entity에 기본적인 CRUD 연산 제공
* Repository에서 `JpaRepository`를 상속하여 사용
* `@Repository` 어노테이션을 추가할 필요 없음

## 기본 제공 기능
* `save()`: 레코드 저장(insert, update)
* `findOne()`: 기본 키로 레코드 1개 찾기
* `findAll()`: 전체 레코드 불러오기
* `count()`: 레코드 개수
* `delete()`: 레코드 삭제

## 기능 추가
* `findBy`: 쿼리를 요청하는 메소드
* `countBy`: 쿼리의 결과로 레코드 수를 요청하는 메소드

## 사용할 수 있는 키워드
* `And`
* `Or`
* `Between`: 필드의 두 값 사이의 항목 검색
* `LessThan`
* `GreaterThanEqual`
* `Like`
* `IsNull`: null인 항목 검색
* `In`
* `OrderBy`: 검색결과를 정렬하여 반환






