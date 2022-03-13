package ru.itsjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itsjava.domain.*;
import ru.itsjava.repository.BookRepository;
import ru.itsjava.repository.AuthorRepository;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class SpringBootJpaFoundationsApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(SpringBootJpaFoundationsApplication.class, args);

        AuthorRepository authorRepository = context.getBean(AuthorRepository.class);

        System.out.println("authorRepository.getById(1L) = " + authorRepository.getById(1L));

        Author author = new Author(0L, "the author is unknown");
        authorRepository.insert(author);
        System.out.println("authorRepository.getById(3L) = " + authorRepository.getById(3L));

        Author author1 = authorRepository.getById(2L);
        author1.setName("the author is unknown");

        authorRepository.update(author1);
        System.out.println("authorRepository.getById(3L) = " + authorRepository.getById(3L));

        authorRepository.delete(3L);
        System.out.println("authorRepository.getById(3L) = " + authorRepository.getById(3L));


        BookRepository bookRepository = context.getBean(BookRepository.class);
        System.out.println("bookRepository.findAll() = " + bookRepository.findAll());

        Author author2 = new Author(0L, "Someone's out there");
        PlaceBook placeBook = new PlaceBook(0L, "Unknown", 1L);
        List<PlaceBook> place2 = new ArrayList<>();
        place2.add(placeBook);

        Book book = new Book(0L, "Russian in Texas", author2, place2);
        bookRepository.insert(book);
        System.out.println("bookRepository.findAll() = " + bookRepository.findAll());

        Book book1 = bookRepository.getById(1L);
        book1.setTitle("Super film");
        bookRepository.update(book1);
        System.out.println("bookRepository.getById(1L) = " + bookRepository.getById(1L));

        bookRepository.delete(2L);
        System.out.println("bookRepository.findAll() = " + bookRepository.findAll());
    }

}
