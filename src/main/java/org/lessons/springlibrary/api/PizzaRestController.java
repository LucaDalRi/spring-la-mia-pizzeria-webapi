package org.lessons.springlibrary.api;

import jakarta.validation.Valid;
import org.lessons.springlibrary.exceptions.PizzaNotFoundException;
import org.lessons.springlibrary.model.Pizza;
import org.lessons.springlibrary.repository.PizzaRepository;
import org.lessons.springlibrary.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/v1/pizze")
public class PizzaRestController {

    @Autowired
    private PizzaRepository pizzaRepository;
    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public List<Pizza> index(@RequestParam Optional<String> keyword) {
        return pizzaService.getAll(keyword);
    }

    @GetMapping("/{id}")
    public Pizza get(@PathVariable Integer id) {
        try {
            return pizzaService.getById(id);
        } catch (PizzaNotFoundException | ChangeSetPersister.NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public Pizza create(@Valid @RequestBody Pizza pizza) {
        return pizzaService.create(pizza);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        pizzaRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Pizza update(@PathVariable Integer id, @Valid @RequestBody Pizza pizza) {
        pizza.setId(id);
        return pizzaRepository.save(pizza);
    }


}
