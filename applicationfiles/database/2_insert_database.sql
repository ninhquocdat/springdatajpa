-- use realestate;
set search_path to realestate;

insert into role(code,name) values('ADMIN','Quản trị hệ thống');
insert into role(code,name) values('USER','Nhân viên');
insert into role(code,name) values('MANAGER','Quản lý');

insert into users(username,password,fullname,createddate,status)
values('admin','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','admin',CURRENT_TIMESTAMP,1);
insert into users(username,password,fullname,createddate,status)
values('user','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','user',CURRENT_TIMESTAMP,1);
insert into users(username,password,fullname,createddate,status)
values('manager','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','manager',CURRENT_TIMESTAMP,1);

INSERT INTO user_role(user_id,role_id) VALUES (1,1);
INSERT INTO user_role(user_id,role_id) VALUES (2,2);
INSERT INTO user_role(user_id,role_id) VALUES (3,3);

