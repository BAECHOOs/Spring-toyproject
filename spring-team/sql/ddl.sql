CREATE USER test_user@localhost IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON user.* TO test_user@localhost;

create table user (id char(10) not null, pw char(20), primary key(id));
insert into user (id, pw) values ("id1", "pass1");
insert into user (id, pw) values ("id2", "pass2");
select * from user;