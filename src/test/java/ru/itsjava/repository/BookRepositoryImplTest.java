package ru.itsjava.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@Import(BookRepositoryImpl.class)
@SuppressWarnings("ALL")
public class BookRepositoryImplTest {
    public static final long DEFAULT_BOOK_ID = 1L;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void shouldHaveCorrectGetById() {
        var expectedBook = entityManager.find(Book.class, DEFAULT_BOOK_ID);
        var actualBook = bookRepository.getById((DEFAULT_BOOK_ID));

        Assertions.assertEquals(expectedBook, actualBook);
    }

    @Test
    public void shouldHaveCorrectFindAllBooks() {
        var expectedBook = entityManager
                .createQuery("select distinct b from books b join fetch b.author join fetch b.placeBook ", Book.class)
                .getResultList();
        var actualBook = bookRepository.findAll();

        Assertions.assertEquals(expectedBook, actualBook);
    }

    @Test
    public void shouldHaveCorrectInsert() {
        var author = new Author(3L, "Mikhailo Staritsky");
        PlaceBook placeOne = new PlaceBook(3L, "Ukraine", 1L);
        List<PlaceBook> placeBooks = new ArrayList<>();
        placeBooks.add(placeOne);
        var book = new Book(2L, "For two hares", author, placeBooks);
        bookRepository.insert(book);
        var actualBook = bookRepository.getById(2L);

        Assertions.assertEquals(book, actualBook);
    }

    @Test
    public void shouldHaveCorrectUpdate() {
        var book = bookRepository.getById(1L);
        book.setTitle("Anna Karenina");
        bookRepository.update(book);
        var actualFilm = bookRepository.getById(1L);

        Assertions.assertEquals("Anna Karenina", actualFilm.getTitle());
    }

    @Test
    public void shouldHaveCorrectDelete() {
        bookRepository.delete(2L);
        var deleteBook = bookRepository.getById(2L);

        Assertions.assertNull(deleteBook);
    }


}
