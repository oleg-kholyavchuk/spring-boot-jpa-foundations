package ru.itsjava.repository;

import ru.itsjava.domain.Book;

import java.util.List;

@SuppressWarnings("ALL")
public interface BookRepository {
    List<Book> findAll();
    Book getById(long id);
    void insert(Book book);
    void update(Book book);
    void delete(long id);
}
