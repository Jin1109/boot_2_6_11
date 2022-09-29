create table vue(
	id	 number(5) primary key,
	name varchar2(10 char),
	age  number(2)
);

create sequence vue_seq;

insert into vue values(vue_seq.nextval, '홍길동', 21);
insert into vue values(vue_seq.nextval, '이순신', 25);
insert into vue values(vue_seq.nextval, '강감천', 10);

select * from vue

drop table vue CASCADE CONSTRAINTS;
drop sequence vue_seq;