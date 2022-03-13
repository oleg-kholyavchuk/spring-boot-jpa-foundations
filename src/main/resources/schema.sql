
drop table if exists author, books, placeBook;

create table author(
id bigint primary key auto_increment,
name varchar(256)
);

create table books(
id bigint primary key auto_increment,
title varchar(256),
author_id bigint references author(id)
);

create table placeBook(
id bigint primary key auto_increment,
name varchar(256),
book_id bigint references books(id)
);

