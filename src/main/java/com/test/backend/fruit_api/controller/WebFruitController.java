package com.test.backend.fruit_api.controller;

import com.test.backend.fruit_api.entity.Fruit;
import com.test.backend.fruit_api.service.FruitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/fruits")
public class WebFruitController {

    @Autowired
    private FruitService fruitService;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("fruits", fruitService.getAllFruits());
        return "index";
    }

    @GetMapping("/create")
    public String showAddFruitForm(Model model) {
        model.addAttribute("fruit", new Fruit());
        return "add-fruit";
    }

    @PostMapping("/add")
    public String addFruit(@Valid Fruit fruit, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-fruit";
        }

        fruitService.createFruit(fruit);
        return "redirect:/fruits";
    }

    @GetMapping("/edit/{id}")
    public String showEditFruitForm(@PathVariable String id, Model model) {
        fruitService.getFruitById(id).ifPresent(fruit -> model.addAttribute("fruit", fruit));
        return "edit-fruit";
    }

    @PutMapping("/edit/{id}")
    public String updateFruit(@PathVariable String id, @Valid Fruit fruit, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit-fruit";
        }

        fruitService.updateFruit(id, fruit);
        return "redirect:/fruits";
    }

    @GetMapping("/delete/{id}")
    public String deleteFruit(@PathVariable String id) {
        fruitService.deleteFruit(id);
        return "redirect:/fruits";
    }

}
