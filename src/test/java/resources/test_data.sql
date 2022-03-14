
insert into author(id, name)
values (1, 'Leo Tolstoy');
insert into author(id, name)
values (2, 'Mikhailo Staritsky');

insert into books(id, title, author_id)
values (1, 'War and Peace', 1);
insert into books (id, title, author_id)
values (2, 'For two hares', 2);

insert into place_book(id, name, book_id)
values (1, 'Russian', 1);
insert into place_book(id, name, book_id)
values (2, 'Ukraine', 2);