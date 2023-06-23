package org.lessons.springlibrary.controller;

import org.lessons.springlibrary.model.Pizza;
import org.lessons.springlibrary.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class PizzaController {


    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping
    public String list(Model model) {
        List<Pizza> pizza = pizzaRepository.findAll();
        model.addAttribute("pizza", pizza);
        return "/pizza";
    }

    @GetMapping("detail/{id}")
    public String detail(@PathVariable("id") Integer pizzaId, Model model) {

        Optional<Pizza> result = pizzaRepository.findById(pizzaId);
        if (result.isPresent()) {
            model.addAttribute("pizza", result.get());
            return "/detail";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }


}
