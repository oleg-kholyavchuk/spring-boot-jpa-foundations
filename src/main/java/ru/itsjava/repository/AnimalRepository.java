package ru.itsjava.repository;

import ru.itsjava.domain.Animal;

import java.util.List;

public interface AnimalRepository {
    List<Animal> findAll();
    Animal getById(long id);
    void insert(Animal animal);
    void update(Animal animal);
    void delete(long id);
}
