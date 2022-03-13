package ru.itsjava.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.itsjava.domain.Author;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class AuthorRepositoryImpl implements AuthorRepository {
    private final EntityManager entityManager;


    @Override
    public Author getById(long id) {
        return entityManager.find(Author.class, id);
    }

    @Override
    public void insert(Author author) {
        if (author.getId() == 0L) {
            entityManager.persist(author);
        } else {
            entityManager.merge(author);
        }
    }

    @Override
    public void update(Author author) {
        entityManager.merge(author);
    }

    @Override
    public void delete(long id) {
        Author authorById = entityManager.find(Author.class, id);
        entityManager.remove(authorById);
    }
}
