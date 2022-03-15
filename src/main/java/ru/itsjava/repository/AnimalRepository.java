package ru.itsjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itsjava.domain.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
