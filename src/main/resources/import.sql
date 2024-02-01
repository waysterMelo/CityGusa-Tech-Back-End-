INSERT INTO tb_user (email, name, PASSWORD) VALUES ('wayster@gmail.com', 'wayster', '123');
INSERT INTO tb_user (email, name, PASSWORD) VALUES ('admin@gmail.com', 'admin', '123');

insert into tb_roles (role) values ('ROLE_ADMIN');
insert into tb_roles (role) values ('ROLE_OPERATOR');

insert into tb_user_role (USER_ID, ROLE_ID) values (1,1);
insert into tb_user_role (USER_ID, ROLE_ID) values (2,2);
