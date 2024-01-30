INSERT INTO tb_user (LOGIN, name, PASSWORD) VALUES ('WAYSTER', 'wayster', '132465')
    INSERT INTO tb_user (LOGIN, name, PASSWORD) VALUES ('GIOVANE', 'giovane', '132')
INSERT INTO tb_user (LOGIN, name, PASSWORD) VALUES ('GERALDO', 'geraldo', '321')

insert into tb_roles (role) values ('ADMIN')
insert into tb_roles (role) values ('USER')
insert into tb_roles (role) values ('MANAGER')

insert into tb_user_role (USER_ID, ROLE_ID) values (1,1)
insert into tb_user_role (USER_ID, ROLE_ID) values (1,3)
insert into tb_user_role (USER_ID, ROLE_ID) values (2,3)
insert into tb_user_role (USER_ID, ROLE_ID) values (2,1)
insert into tb_user_role (USER_ID, ROLE_ID) values (3,3)