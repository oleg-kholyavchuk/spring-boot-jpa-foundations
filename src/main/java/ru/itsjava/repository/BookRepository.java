package ru.itsjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itsjava.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
