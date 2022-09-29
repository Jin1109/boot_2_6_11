drop table comments3 cascade constraints purge;

create table comments3(
  num          number       primary key,
  id           varchar2(30) references member(id),
  content      varchar2(200),
  reg_date     date,
  board_num    number references board2(board_num) 
               on delete cascade 
  );
drop sequence com_seq3;
create sequence com_seq3;

select * from comments3;