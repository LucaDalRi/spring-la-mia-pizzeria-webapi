package org.lessons.springlibrary.controller;

import jakarta.validation.Valid;
import org.lessons.springlibrary.model.OffertaSpeciale;
import org.lessons.springlibrary.model.Pizza;
import org.lessons.springlibrary.repository.OffertaSpecialeRepository;
import org.lessons.springlibrary.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class OffertaSpecialeController {


    @Autowired
    private PizzaRepository pizzaRepository;
    @Autowired
    private OffertaSpecialeRepository offertaSpecialeRepository;

    @GetMapping("/offertaCreate")
    public String create(Model model, @RequestParam("id") Integer id) {
        OffertaSpeciale offertaSpeciale = new OffertaSpeciale();
        Optional<Pizza> pizza = pizzaRepository.findById(id);
        offertaSpeciale.setPizza(pizza.get());
        model.addAttribute("offertaSpeciale", offertaSpeciale);
        return "/offertaCreate";
    }

    @PostMapping("/offertaCreate")
    public String store(
            @Valid @ModelAttribute("offertaSpeciale") OffertaSpeciale formOffertaSpeciale,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            return "/offertaCreate";
        }
        offertaSpecialeRepository.save(formOffertaSpeciale);
        return "redirect:/list";
    }

}
