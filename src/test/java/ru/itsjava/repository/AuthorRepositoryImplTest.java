package ru.itsjava.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@DataJpaTest
@Import(AuthorRepositoryImpl.class)
@SuppressWarnings("ALL")
public class AuthorRepositoryImplTest {
    public static final long DEFAULT_FILM_ID = 1L;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void shouldHaveCorrectGetById() {
        var expectedAuthor = entityManager.find(Author.class, DEFAULT_FILM_ID);
        var actualAuthor = authorRepository.getById((DEFAULT_FILM_ID));

        Assertions.assertEquals(expectedAuthor, actualAuthor);
    }

    @Test
    public void shouldHaveCorrectInsert() {
        var author = new Author(3L, "the author is unknown");
        authorRepository.insert(author);
        var actualAuthor = authorRepository.getById(3L);

        Assertions.assertEquals(author, actualAuthor);
    }

    @Test
    public void shouldHaveCorrectUpdate() {
        var author = authorRepository.getById(1L);
        author.setName("the author is unknown");
        authorRepository.update(author);
        var actualAuthor = authorRepository.getById(1L);

        Assertions.assertEquals("the author is unknown", actualAuthor.getName());
    }

    @Test
    public void shouldHaveCorrectDeleteById() {
        authorRepository.delete(2L);
        var deleteAuthor = authorRepository.getById(2L);

        Assertions.assertNull(deleteAuthor);
    }
}
