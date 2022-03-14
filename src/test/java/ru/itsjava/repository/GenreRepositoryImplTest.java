package ru.itsjava.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.Film;
import ru.itsjava.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@DataJpaTest
@Import(GenreRepositoryImpl.class)
public class GenreRepositoryImplTest {
    public static final long DEFAULT_GENRE_ID = 1L;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    public void shouldHaveCorrectGetById() {
        var expectedGenre = entityManager.find(Genre.class, DEFAULT_GENRE_ID);
        var actualGenre = genreRepository.getById((DEFAULT_GENRE_ID));

        Assertions.assertEquals(expectedGenre, actualGenre);
    }

    @Test
    public void shouldHaveCorrectInsert() {
        var comedy = new Genre(4L, "Comedy");
        genreRepository.insert(comedy);
        var actualGenre = genreRepository.getById(4L);

        Assertions.assertEquals(comedy, actualGenre);

    }

    @Test
    public void shouldHaveCorrectUpdate() {
        var genre = genreRepository.getById(1L);
        genre.setName("Comedy");
        genreRepository.update(genre);
        var actualGenre = genreRepository.getById(1L);

        Assertions.assertEquals("Comedy", actualGenre.getName());
    }

    @Test
    public void shouldHaveCorrectDeleteById() {
        genreRepository.delete(2L);
        var deleteGenre = genreRepository.getById(2L);

        Assertions.assertNull(deleteGenre);
    }


}
