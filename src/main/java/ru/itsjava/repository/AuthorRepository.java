package ru.itsjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itsjava.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
