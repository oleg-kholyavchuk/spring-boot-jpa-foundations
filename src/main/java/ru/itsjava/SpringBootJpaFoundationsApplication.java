package ru.itsjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itsjava.domain.*;
import ru.itsjava.repository.AnimalRepository;
import ru.itsjava.repository.PetRepository;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class SpringBootJpaFoundationsApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(SpringBootJpaFoundationsApplication.class, args);

        PetRepository petRepository = context.getBean(PetRepository.class);
        System.out.println("petRepository.getById(1L) = " + petRepository.getById(1L));

        Pet pet = new Pet(0L, "Dog");
        petRepository.insert(pet);
        System.out.println("petRepository.getById(3L) = " + petRepository.getById(3L));

        Pet pet1 = petRepository.getById(2L);
        pet1.setPet("Mouse");
        petRepository.update(pet1);
        System.out.println("petRepository.getById(2L) = " +petRepository.getById(2L));

        petRepository.deleteById(3L);
        System.out.println("petRepository.getById(3L) = " + petRepository.getById(3L));

        AnimalRepository animalRepository = context.getBean(AnimalRepository.class);
        System.out.println("animalRepository.findAll() = " + animalRepository.findAll());

        Pet pet2 = new Pet(0L, "Cat");
        BreedingPlace breedingPlace = new BreedingPlace(0L, "Unknown", 1L);
        List<BreedingPlace> breedingPlaceList = new ArrayList<>();
        breedingPlaceList.add(breedingPlace);

        Animal animal = new Animal(0L, "Russian", pet2, breedingPlaceList);
        animalRepository.insert(animal);
        System.out.println("animalRepository.findAll() = " + animalRepository.findAll());

        Animal animal1 = animalRepository.getById(1L);
        animal1.setBreed("USA");
        animalRepository.update(animal1);
        System.out.println("animalRepository.getById(1L) = " + animalRepository.getById(1L));

        animalRepository.delete(2L);
        System.out.println("animalRepository.findAll() = " + animalRepository.findAll());
    }
}
