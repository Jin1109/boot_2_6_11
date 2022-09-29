drop table emp01 cascade constraints purge;


create table emp01
as
select * from emp;


drop table temp cascade constraints purge;


create table temp
as
select ename, job, empno from emp
where 1 = 0;

drop trigger emp01_temp;

create or replace trigger emp01_temp
after delete
on emp01
for each row
begin
	insert into temp
	values(:old.ename, :old.job, :old.empno);
end;

select * from emp01;

select * from temp;

delete emp01 where deptno=20;

