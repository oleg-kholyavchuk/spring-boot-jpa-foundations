insert into genre(id, name)
values (1, 'fantasy');
insert into genre(id, name)
values (2, 'fantastic');

insert into films (id, title, genre_id)
values (1, 'Harry Potter', 1);
insert into films (id, title, genre_id)
values (2, 'Star Wars', 2);

insert into place (id, name, film_id)
values (1, 'London', 1);
insert into place (id, name, film_id)
values (2, 'Train', 1);
insert into place (id, name, film_id)
values (3, 'Hollywood', 2);