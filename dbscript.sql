create table author_model (id bigint generated by default as identity, first_name varchar(255), last_name varchar(255), primary key (id))
create table book_model (author_id bigint not null, genre_id bigint not null, id bigint generated by default as identity, order_id bigint not null, store_id bigint not null, type_id bigint not null, name varchar(255), primary key (id))
create table genre (id bigint generated by default as identity, name varchar(255), primary key (id))
create table model_user (active boolean not null, id bigint not null, name varchar(255), password varchar(255), username varchar(255), primary key (id))
create table order (id bigint generated by default as identity, type_id bigint not null, primary key (id))
create table order_users (order_id bigint not null, users_id bigint not null)
create table order_type_model (id bigint generated by default as identity, type varchar(255), primary key (id))
create table store_model (id bigint generated by default as identity, address varchar(255), primary key (id))
create table type_model (id bigint generated by default as identity, name varchar(255), primary key (id))
create table user_role (user_id bigint not null, roles varchar(255) check (roles in ('USER','ADMIN','MANAGER')))
alter table if exists book_model add constraint FK250nqw69as8tvrpsodm7pvkec foreign key (author_id) references author_model
alter table if exists book_model add constraint FKj96jo8oyhffpsa8l2mda4rx0r foreign key (genre_id) references genre
alter table if exists book_model add constraint FKdi7klkvxp0taf4ls48mwf64by foreign key (order_id) references order
alter table if exists book_model add constraint FKk0x7p69hahry22lu4uefs1u49 foreign key (store_id) references store_model
alter table if exists book_model add constraint FK55g091vl8dpx9lnm73lipt06o foreign key (type_id) references type_model
alter table if exists order add constraint FKi3lguy6okuolrvq0p9l59gvtx foreign key (type_id) references order_type_model
alter table if exists order_users add constraint FKpl8qcsj7d16h6s3nr5c5kyxyl foreign key (users_id) references model_user
alter table if exists order_users add constraint FKbyky7mnaf06cujgrd76ya874a foreign key (order_id) references order
alter table if exists user_role add constraint FKhnk3nw6rsvkly3ww7umdq7ys1 foreign key (user_id) references model_user
