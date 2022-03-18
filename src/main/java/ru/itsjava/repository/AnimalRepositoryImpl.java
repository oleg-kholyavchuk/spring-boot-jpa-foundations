package ru.itsjava.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itsjava.domain.Animal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
@RequiredArgsConstructor
public class AnimalRepositoryImpl implements AnimalRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Animal> findAll() {
        return entityManager
                .createQuery("select distinct a from animal a join fetch a.pet join fetch a.breedingPlace", Animal.class)
                .getResultList();
    }

    @Override
    public Animal getById(long id) {
        return entityManager.find(Animal.class, id);
    }

    @Override
    public void insert(Animal animal) {
        if (animal.getId() == 0L) {
            entityManager.persist(animal);
        } else {
            entityManager.merge(animal);
        }
    }

    @Override
    public void update(Animal animal) {
        entityManager.merge(animal);
    }

    @Override
    public void delete(long id) {
        Animal animalById = entityManager.find(Animal.class, id);
        entityManager.remove(animalById);
    }
}
