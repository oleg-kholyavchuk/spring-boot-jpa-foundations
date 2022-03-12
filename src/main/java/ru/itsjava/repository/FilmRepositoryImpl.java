package ru.itsjava.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.itsjava.domain.Film;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class FilmRepositoryImpl implements FilmRepository {
    private final EntityManager entityManager;

    @Override
    public List<Film> findAll() {
        return entityManager
                .createQuery("select distinct f from films f join fetch f.genre join fetch f.placeList ", Film.class)
                .getResultList();
    }

    @Override
    public void insert(Film film) {
        if (film.getId() == 0L) {
            entityManager.persist(film);
        } else {
            entityManager.merge(film);
        }
    }

    @Override
    public void update(Film film) {
        entityManager.merge(film);
    }

    @Override
    public void delete(long id) {
        Film filmById = entityManager.find(Film.class, id);
        entityManager.remove(filmById);
    }
}
