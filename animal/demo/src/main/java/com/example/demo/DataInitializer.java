package com.example.demo;

import com.example.demo.model.Animal;
import com.example.demo.repository.AnimalRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final AnimalRepository animalRepository;

    public DataInitializer(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (animalRepository.count() == 0) {
            animalRepository.save(new Animal("Rex", "Cachorro", 5));
            animalRepository.save(new Animal("Mimi", "Gato", 3));
            animalRepository.save(new Animal("Dourado", "Peixe", 1));
        }
    }
}
