package ru.itsjava.repository;

import ru.itsjava.domain.Film;

import java.util.List;

public interface FilmRepository {
    List<Film> findAll();
    Film getById(long id);
    void insert(Film film);
    void update(Film film);
    void delete(long id);
}
