drop table if exists pet, animal, breeding_place;

create table pet(
id bigint primary key auto_increment,
pet varchar(256)
);

create table animal(
id bigint primary key auto_increment,
breed varchar(256),
pet_id bigint references pet(id)
);

create table breeding_place(
id bigint primary key auto_increment,
name varchar(256),
animal_id bigint references animal(id)
);



