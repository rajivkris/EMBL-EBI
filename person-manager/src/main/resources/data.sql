insert into users (username, password, enabled) values ('bob', '{noop}123', true);
insert into authorities (username, authority) values ('bob', 'ROLE_USER');

insert into users (username, password, enabled) values ('admin', '{noop}password', true);
insert into authorities (username, authority) values ('admin', 'ROLE_ADMIN');