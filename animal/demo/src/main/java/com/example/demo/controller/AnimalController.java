package com.example.demo.controller;

import com.example.demo.model.Animal;
import com.example.demo.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/animals")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    // Listar todos os animais
    @GetMapping
    public String list(Model model) {
        model.addAttribute("animals", animalService.listarTodos());
        return "list";
    }

    // Ver detalhes de um animal
    @GetMapping("/view/{id}")
    public String view(@PathVariable Long id, Model model) {
        model.addAttribute("animal", animalService.buscarPorId(id));
        return "view";
    }

    // Formulário de novo animal
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("animal", new Animal());
        return "form";
    }

    // Salvar novo animal
    @PostMapping
    public String save(@ModelAttribute Animal animal) {
        animalService.salvar(animal);
        return "redirect:/animals";
    }

    // Formulário de edição
    @GetMapping("/edit/{id}")
    public String editarAnimal(@PathVariable Long id, Model model) {
        Animal animal = animalService.buscarPorId(id);
        if (animal == null) {
            throw new IllegalArgumentException("Animal não encontrado!");
        }
        model.addAttribute("animal", animal);
        return "edit";
    }

    // Salvar alterações
    @PostMapping("/edit/{id}")
    public String salvarEdicaoAnimal(
            @PathVariable Long id,
            @ModelAttribute Animal animalAtualizado,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("animal", animalAtualizado);
            return "edit";
        }

        animalService.editarAnimal(id, animalAtualizado);
        return "redirect:/animals";
    }

    // Excluir animal
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        animalService.deletar(id);
        return "redirect:/animals";
    }
}
