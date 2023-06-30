package org.lessons.springlibrary.controller;

import jakarta.validation.Valid;
import org.lessons.springlibrary.model.OffertaSpeciale;
import org.lessons.springlibrary.model.Pizza;
import org.lessons.springlibrary.repository.OffertaSpecialeRepository;
import org.lessons.springlibrary.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/offerta")
public class OffertaSpecialeController {


    @Autowired
    private PizzaRepository pizzaRepository;
    @Autowired
    private OffertaSpecialeRepository offertaSpecialeRepository;

    @GetMapping("/create")
    public String create(Model model, @RequestParam("pizzaId") Integer pizzaId) {
        OffertaSpeciale offertaSpeciale = new OffertaSpeciale();
        Optional<Pizza> pizza = pizzaRepository.findById(pizzaId);
        offertaSpeciale.setPizza(pizza.get());
        model.addAttribute("offertaSpeciale", offertaSpeciale);
        return "/offerta/create";
    }

    @PostMapping("/create")
    public String store(
            @Valid @ModelAttribute("offertaSpeciale") OffertaSpeciale formOffertaSpeciale,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            return "/offerta/create";
        }
        offertaSpecialeRepository.save(formOffertaSpeciale);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        OffertaSpeciale offertaSpeciale = getOffertaById(id);
        model.addAttribute("offertaSpeciale", offertaSpeciale);
        return "/offerta/edit";
    }

    @PostMapping("/edit/{id}")
    public String doEdit(
            @PathVariable Integer id,
            @Valid @ModelAttribute("offertaSpeciale") OffertaSpeciale formOffertaSpeciale,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        OffertaSpeciale offertaToEdit = getOffertaById(id);
        if (bindingResult.hasErrors()) {
            return "/offerta/edit";
        }
        formOffertaSpeciale.setId(offertaToEdit.getId());
        offertaSpecialeRepository.save(formOffertaSpeciale);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        OffertaSpeciale offertaToDelete = getOffertaById(id);
        offertaSpecialeRepository.delete(offertaToDelete);
        return "redirect:/";
    }

    private OffertaSpeciale getOffertaById(Integer id) {
        Optional<OffertaSpeciale> result = offertaSpecialeRepository.findById(id);
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "offerta con id " + id + " non trovato");
        }
        return result.get();
    }

}
