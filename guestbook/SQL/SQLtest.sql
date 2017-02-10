--query test

--insert
insert
	into guestbook 
values(seq_guestbook.nextval,'봉봉','123','우당탕!',sysdate);

--delete
--해보기

commit;

--select
SELECT no, name, content, to_char(reg_date,'yyyy-dd-mm') from guestbook order by reg_date desc;   

--update
update guestbook set name='a',content='a' where no=8;