package org.lessons.springlibrary.controller;

import org.lessons.springlibrary.model.Pizza;
import org.lessons.springlibrary.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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


}
