package ru.itsjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itsjava.domain.Film;
import ru.itsjava.domain.Genre;
import ru.itsjava.domain.Place;
import ru.itsjava.repository.FilmRepository;
import ru.itsjava.repository.GenreRepository;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class SpringBootJpaFoundationsApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(SpringBootJpaFoundationsApplication.class, args);

        GenreRepository genreRepository = context.getBean(GenreRepository.class);

        System.out.println("genreRepository.getById(1L) = " + genreRepository.getById(1L));

        Genre genre = new Genre(0L, "western");
        genreRepository.insert(genre);
        System.out.println("genreRepository.getById(3L) = " + genreRepository.getById(3L));

        Genre genre3 = genreRepository.getById(2L);
        genre3.setName("Western");

        genreRepository.update(genre3);
        System.out.println("genreRepository.getById(3L) = " + genreRepository.getById(2L));


        genreRepository.delete(3L);
        System.out.println("genreRepository.getById(3L) = " + genreRepository.getById(3L));

        FilmRepository filmRepository = context.getBean(FilmRepository.class);
        System.out.println("filmRepository.findAll() = " + filmRepository.findAll());

        Place placeOne = new Place(0L, "Texas", 1L);
        List<Place> place = new ArrayList<>();
        place.add(placeOne);

        Film film = new Film(0L, "Russian in Texas", genre, place);
        filmRepository.insert(film);
        System.out.println("filmRepository.findAll() = " + filmRepository.findAll());

        Film film1 = filmRepository.findAll().get(0);
        film1.setTitle("Super film");
        filmRepository.update(film1);
        System.out.println("filmRepository.findAll() = " + filmRepository.findAll());

        filmRepository.delete(3L);
        System.out.println("genreRepository.getById(3L) = " + filmRepository.findAll());

    }

}
