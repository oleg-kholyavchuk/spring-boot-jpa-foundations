package ru.itsjava.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("ALL")
@Entity(name = "films")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;

    @ManyToOne(targetEntity = Genre.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @OneToMany(targetEntity = Place.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "film_id")
    private List<Place> placeList;
}
