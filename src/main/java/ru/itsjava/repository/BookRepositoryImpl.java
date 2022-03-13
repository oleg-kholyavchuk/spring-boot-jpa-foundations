package ru.itsjava.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.itsjava.domain.Book;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {
    private final EntityManager entityManager;

    @Override
    public List<Book> findAll() {
        return entityManager
                .createQuery("select distinct b from books b join fetch b.author join fetch b.placeBook ", Book.class)
                .getResultList();
    }

    @Override
    public Book getById(long id) {
        return entityManager.find(Book.class, id);
    }

    @Override
    public void insert(Book book) {
        if (book.getId() == 0L) {
            entityManager.persist(book);
        } else {
            entityManager.merge(book);
        }
    }

    @Override
    public void update(Book book) {
        entityManager.merge(book);
    }

    @Override
    public void delete(long id) {
        Book bookById = entityManager.find(Book.class, id);
        entityManager.remove(bookById);
    }
}
