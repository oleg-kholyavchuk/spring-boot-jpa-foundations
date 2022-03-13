package ru.itsjava.repository;

import ru.itsjava.domain.Author;

@SuppressWarnings("ALL")
public interface AuthorRepository {
    Author getById(long id);
    void insert(Author author);
    void update(Author author);
    void delete(long id);
}
