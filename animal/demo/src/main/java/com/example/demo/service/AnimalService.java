package com.example.demo.service;

import com.example.demo.model.Animal;
import com.example.demo.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    // Listar todos os animais
    public List<Animal> listarTodos() {
        return animalRepository.findAll();
    }

    // Buscar animal por ID
    public Animal buscarPorId(Long id) {
        return animalRepository.findById(id).orElse(null);
    }

    // Salvar novo animal
    public Animal salvar(Animal animal) {
        return animalRepository.save(animal);
    }

    // Editar animal existente (baseado no exemplo do teu colega)
    public Animal editarAnimal(Long id, Animal animalAtt) {
        return animalRepository.findById(id).map(existing -> {
            existing.setNome(animalAtt.getNome());
            existing.setEspecie(animalAtt.getEspecie());
            existing.setIdade(animalAtt.getIdade());
            return animalRepository.save(existing);
        }).orElse(null);
    }

    // Excluir animal
    public void deletar(Long id) {
        animalRepository.deleteById(id);
    }
}
