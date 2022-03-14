package ru.itsjava.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.Film;
import ru.itsjava.domain.Genre;
import ru.itsjava.domain.Place;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@Import(FilmRepositoryImpl.class)
public class FilmRepositoryImplTest {
    public static final long DEFAULT_FILM_ID = 1L;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private FilmRepository filmRepository;

    @Test
    public void shouldHaveCorrectGetById() {
        var expectedFilm = entityManager.find(Film.class, DEFAULT_FILM_ID);
        var actualFilm = filmRepository.getById((DEFAULT_FILM_ID));

        Assertions.assertEquals(expectedFilm, actualFilm);
    }

    @Test
    public void shouldHaveCorrectFindAllFilms() {
        var expectedFilms = entityManager
                .createQuery("select distinct f from films f join fetch f.genre join fetch f.placeList ", Film.class)
                .getResultList();

        var actualFilms = filmRepository.findAll();

        Assertions.assertEquals(expectedFilms, actualFilms);
    }

    @Test
    public void shouldHaveCorrectInsert() {
        var comedy = new Genre(3L, "Comedy");
        Place placeOne = new Place(4L, "Texas", 1L);
        List<Place> place = new ArrayList<>();
        place.add(placeOne);
        var film = new Film(2L, "Comedy", comedy, place);
        filmRepository.insert(film);
        var actualFilm = filmRepository.getById(2L);

        Assertions.assertEquals(film, actualFilm);
    }

    @Test
    public void shouldHaveCorrectUpdate() {
        var film = filmRepository.getById(1L);
        film.setTitle("Red dawn");
        filmRepository.update(film);
        var actualFilm = filmRepository.getById(1L);

        Assertions.assertEquals("Red dawn", actualFilm.getTitle());
    }

    @Test
    public void shouldHaveCorrectDelete() {
        filmRepository.delete(2L);
        var deleteGenre = filmRepository.getById(2L);

        Assertions.assertNull(deleteGenre);
    }


}
