--create sequence
drop SEQUENCE SEQ_guestbook;

create sequence seq_guestbook
start with 1
increment by 1
MAXVALUE 9999999999;