use baechoo;
truncate table Product;
Insert into Product (product_id, user_id, name, price, description, picture_url, views, on_sale, like_count) values(0, 2, '양배추', 2000, '양배추 맛있어요.', 'https://user-images.githubusercontent.com/38900338/107228177-b2d9fd00-6a5f-11eb-95ff-9bffa69edbc1.jpg', 0, true, 0);
Insert into Product (product_id, user_id, name, price, description, picture_url, views, on_sale, like_count) values(0, 4, '딸기', 1000, '딸기 팔아요.', 'https://user-images.githubusercontent.com/38900338/107228323-e1f06e80-6a5f-11eb-94ab-ae5bb28868e9.jpg', 0, true, 0);
Insert into Product (product_id, user_id, name, price, description, picture_url, views, on_sale, like_count) values(0, 6, '귤', 5000, '귤 팝니다.', 'https://user-images.githubusercontent.com/38900338/107228379-f5033e80-6a5f-11eb-9990-a4fc899865d6.jpg', 0, true, 0);
Insert into Product (product_id, user_id, name, price, description, picture_url, views, on_sale, like_count) values(0, 8, '양파', 3000, '양파는 매워요.ㅠㅠ', 'https://user-images.githubusercontent.com/38900338/107228646-3bf13400-6a60-11eb-98c4-f9765852bc07.jpg', 0, true, 0);
Insert into Product (product_id, user_id, name, price, description, picture_url, views, on_sale, like_count) values(0, 8, '사과', 4000, '사과는 달아요!!', 'https://user-images.githubusercontent.com/38900338/107228519-18c68480-6a60-11eb-9df8-e1ecc8ced4a0.jpg', 0, true, 0);

-- Update product set description = "쌉니다." where product_id=2;